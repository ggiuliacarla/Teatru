package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.domain.Rezervare;
import org.example.domain.Spectacol;
import org.example.services.Service;

import java.util.ArrayList;
import java.util.List;

public class RezervareController {


    private List<Integer> locuriSelectate = new ArrayList<>();
    private LoginController loginController;

    @FXML
    private TextArea detaliiLocTextArea;

    @FXML
    private TextField numeTextField,prenumeTextField,telefonTextField;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Service service;
    public void setService(Service service) {
        this.service=service;
    }

    public void initializeWindow(Stage window) {
        Spectacol spectacolAzi = service.findSpectacolAzi();
        if (spectacolAzi != null) {
            window.setTitle(spectacolAzi.getNume());  // presupunând că există o metodă getNume() în Spectacol
        } else {
            window.setTitle("Niciun spectacol astăzi");
        }
    }


    @FXML
    private void handleSeatSelection(javafx.event.ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        int seatId = Integer.parseInt(selectedButton.getText());

        // Adăugăm locul selectat în lista locurilor selectate
        locuriSelectate.add(seatId);

        // Fetch the seat details from the service using the seatId
        String seatDetails = service.getSeatDetailsById(seatId);

        // Display the seat details in the TextArea
        detaliiLocTextArea.setText(seatDetails);
    }

    @FXML
    private void handleReservation() {
        // Extragem numele, prenumele și telefonul din câmpurile text
        String nume = numeTextField.getText();
        String prenume = prenumeTextField.getText();
        String telefon = telefonTextField.getText();
        // Verificăm dacă există deja o rezervare pentru numărul de telefon specificat
        if (!service.isReservationExistsForPhoneNumber(telefon)) {
            // Pentru fiecare loc selectat, facem rezervare
            Rezervare rezervare=service.addRezervare(nume,prenume,telefon);
            for (Integer seatId : locuriSelectate) {
                // Facem rezervarea pentru locul curent
                service.makeReservation(seatId,rezervare.getId()) ;
            }

        // După ce am făcut toate rezervările, golim lista locurilor selectate
        locuriSelectate.clear();
        detaliiLocTextArea.clear();

        // Afisăm un mesaj că rezervarea a fost efectuată cu succes
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rezervare cu succes");
        alert.setHeaderText(null);
        alert.setContentText("Rezervarea a fost efectuată cu succes!");
        alert.showAndWait();

        // Și putem reseta și TextArea, după caz
        detaliiLocTextArea.clear();
    } else  {
        // În caz de eroare la rezervare, afișăm un mesaj de eroare
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare la rezervare");
        alert.setHeaderText(null);
        alert.setContentText("A apărut o eroare la efectuarea rezervării. Vă rugăm să încercați din nou mai târziu.");
        alert.showAndWait();
    }
    }

    @FXML
    private void handleResetSelection() {
        // Ștergem toate locurile selectate din lista
        locuriSelectate.clear();

        // Și putem reseta și TextArea, după caz
        detaliiLocTextArea.clear();
    }

    @FXML
    private void handleDeleteReservation() {
        String telefon = telefonTextField.getText();

        if (telefon == null || telefon.trim().isEmpty()) {
            showAlert("Eroare", "Numărul de telefon nu poate fi gol.", Alert.AlertType.ERROR);
            return;
        }

        boolean deleted = service.deleteReservationByTelefon(telefon);

        if (deleted) {
            showAlert("Succes", "Rezervarea a fost ștearsă cu succes.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Eroare", "Nu s-a găsit nicio rezervare cu acest număr de telefon.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleUpdateReservation(){
        String telefon = telefonTextField.getText();

        if (telefon == null || telefon.trim().isEmpty()) {
            showAlert("Eroare", "Numărul de telefon nu poate fi gol.", Alert.AlertType.ERROR);
            return;
        }

        boolean deleted = service.deleteReservationByTelefon(telefon);

        if (deleted) {
            showAlert("Succes", "Rezervarea a fost ștearsă cu succes.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Eroare", "Nu s-a găsit nicio rezervare cu acest număr de telefon.", Alert.AlertType.ERROR);
        }

        handleReservation();
    }

    // Helper method to show alerts
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.domain.Spectacol;
import org.example.services.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdauagareController {

    @FXML
    private TextField numeSpectacolField;

    @FXML
    private TextField genField;

    @FXML
    private DatePicker dataField;

    @FXML
    private TextField oraField;

    @FXML
    private TextField durataField;

    @FXML
    private TextField pretField;

    @FXML
    private Button logout;
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Service service;
    public void setService(Service service) {
        this.service=service;
    }

    @FXML
    private void handleAdd() {
        try {
            String nume = numeSpectacolField.getText();
            String gen = genField.getText();
            LocalDate data = dataField.getValue();
            String oraText = oraField.getText();
            String durataText = durataField.getText();
            String pretText = pretField.getText();

            // Verificăm dacă toate câmpurile sunt completate
            if (nume.isEmpty() || gen.isEmpty() || data == null || oraText.isEmpty() || durataText.isEmpty() || pretText.isEmpty()) {
                showError("Toate câmpurile trebuie completate.");
                return;
            }

            // Verificăm dacă ora este validă
            LocalTime ora;
            try {
                ora = LocalTime.parse(oraText, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                showError("Ora trebuie să fie în formatul HH:mm.");
                return;
            }

            LocalDateTime dataOra = LocalDateTime.of(data, ora);

            // Verificăm dacă data și ora sunt în trecut (permitem introducerea datei de azi)
            if (dataOra.isBefore(LocalDateTime.now().minusMinutes(1))) {
                showError("Data și ora spectacolului trebuie să fie în viitor.");
                return;
            }

            // Convertim LocalDateTime la String folosind un DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dataOraString = dataOra.format(formatter);

            // Verificăm dacă durata și prețul sunt valori numerice valide
            float durata;
            int pret;
            try {
                durata = Float.parseFloat(durataText);
            } catch (NumberFormatException e) {
                showError("Durata trebuie să fie un număr valid.");
                return;
            }

            try {
                pret = Integer.parseInt(pretText);
            } catch (NumberFormatException e) {
                showError("Prețul trebuie să fie un număr valid.");
                return;
            }

            // Creăm obiectul Spectacol cu data sub formă de String
            Spectacol spectacol = new Spectacol(nume, gen, pret, durata, dataOraString);
            service.addSpectacol(spectacol);

            // Curățăm formularul
            numeSpectacolField.clear();
            genField.clear();
            dataField.setValue(null);
            oraField.clear();
            durataField.clear();
            pretField.clear();
        } catch (Exception e) {
            // Gestionăm excepțiile (de exemplu, afișăm un mesaj de eroare)
            e.printStackTrace();
            showError("A apărut o eroare. Vă rugăm să verificați datele introduse.");
        }
    }






    @FXML
    private void handleLogout() {
        if (loginController != null) {
            loginController.clearFields();
        }

        Scene scene = logout.getScene();
        if (scene != null) {

            Stage stage = (Stage) scene.getWindow();
            if (stage != null) {

                stage.close();
            }
        }
    }

    private void showError(String message) {
        // Afișăm un mesaj de eroare într-un Alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

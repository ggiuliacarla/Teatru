package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import org.example.domain.Administrator;
import org.example.services.Service;
public class LoginController {
    private Service service;

    @FXML
    private TextField nume;
    @FXML
    private PasswordField parola;

    @FXML
    private Button logIn;

    public void clearFields() {
        nume.clear();
        parola.clear();
    }

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private void handleLogIn() {
        String numeUtilizator = nume.getText();
        String parolaUtilizator = parola.getText();


        Administrator persoana = service.findUser(numeUtilizator,parolaUtilizator);

        if (persoana != null) {

            openCumparareWindow();
        } else {

            showAlert("Eroare", "Autentificare esuata", "Nume de utilizator sau parola incorecte.");
        }
    }

    @FXML
    private void handleVizitare() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rezervare.fxml"));
            Pane myPane = (Pane) loader.load();

            RezervareController rezervareController = loader.getController();
            rezervareController.setService(service);
            rezervareController.setLoginController(this);

            Stage cumparareStage = new Stage();
            rezervareController.initializeWindow(cumparareStage);  // Setează titlul ferestrei aici

            cumparareStage.setScene(new Scene(myPane));
            cumparareStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void openCumparareWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/adaugare.fxml"));
            Pane myPane=(Pane)loader.load();

            AdauagareController adauagareController = loader.getController();
            adauagareController.setService(service);
            adauagareController.setLoginController(this);

            Stage cumparareStage = new Stage();
            cumparareStage.setScene(new Scene(myPane));
            cumparareStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


}

package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.domain.Spectacol;
import org.example.persistance.*;
import org.example.services.Service;



import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import static javafx.application.Application.launch;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("testez");
        IAdministratorRepository administratorRepository=new AdministratorRepository();

        // System.out.println(administratorRepository.findOne(1));
        //System.out.println(administratorRepository.logIn("Administrator2","test"));

        ISpectacolRepository spectacolRepository=new SpectacolRepository();
        Spectacol spectacol=new Spectacol("a","a",5,5F, "default");
        //spectacolRepository.save(spectacol);
        //System.out.println(spectacolRepository.getAll());
        ILocRepository locRepository=new LocRepository();
        IRezervareRepository rezervareRepository=new RezervareRepository();
        Service service=new Service(administratorRepository,spectacolRepository,locRepository,rezervareRepository);



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/login.fxml"));
        Pane myPane=(Pane)loader.load();

        LoginController loginController = loader.getController();
        loginController.setService(service);


        Scene scene=new Scene(myPane);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);

    }

}
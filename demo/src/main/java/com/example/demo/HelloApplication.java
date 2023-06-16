package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


//        ServerThread serverThread = new ServerThread("localhost",522);
//        serverThread.setDaemon(true);
//        serverThread.start();
//        ClientGuiReceiver receiver = new ClientGuiReceiver () ;
//
//        serverThread.setReceiver(receiver);
//
//        FXMLLoader fxmlLoader = new FXMLLoader(com.example.client.HelloApplication.class.getResource("hello-view.fxml"));
//        fxmlLoader.setControllerFactory(controllerClass -> new HelloController(serverThread, receiver));
//
//
//        stage.setTitle("Hello!");
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.demo;

import com.example.client.ServerThread;
import com.example.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.function.Consumer;

public class HelloController {
    public TextField addressField;
    public TextField portField;
    public ColorPicker colorPicker;
    public Slider radiusSlider;
    public Canvas canvas;
    private Server server;
    private ServerThread serverThread;
    private GraphicsContext graphicsContext;
    @FXML
    private Label welcomeText;

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public Slider getRadiusSlider() {
        return radiusSlider;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void onStartServerClicked(ActionEvent actionEvent) {
        String host = addressField.getText().isEmpty() ? "localhost" : addressField.getText();
        int port = portField.getText().isEmpty() ? 5000 : Integer.parseInt(portField.getText());
        server = new Server(port);
        server.listen();
        serverThread = new ServerThread(host, port);
        serverThread.setDots(d -> {
            graphicsContext.setFill(d.color());
            graphicsContext.fillOval(d.centerY(), d.centerY(), d.radius(), d.radius());
        });
        serverThread.start();
        System.out.println("Server started on port: " + port);

    }
    @FXML
    public void initialize(){
        graphicsContext = canvas.getGraphicsContext2D();
    }
    @FXML
    public void onConnectClicked(ActionEvent actionEvent) {
        String host = addressField.getText().isEmpty() ? "localhost" : addressField.getText();
        int port = portField.getText().isEmpty() ? 5000 : Integer.parseInt(portField.getText());
        serverThread.setDots(d -> {
            graphicsContext.setFill(d.color());
            graphicsContext.fillOval(d.centerX(), d.centerY(), d.radius(), d.radius());});
        serverThread = new ServerThread(host, port);
        System.out.println("connected on port: " + port);
    }
    @FXML
    private void onMouseClicked(MouseEvent event) {
//
//        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        graphicsContext.setFill(colorPicker.getValue());
//        graphicsContext.fillOval(x, y, radiusSlider.getValue(), radiusSlider.getValue());

        serverThread.send(event.getX(), event.getY() , colorPicker.getValue(), radiusSlider.getValue());
    }
}
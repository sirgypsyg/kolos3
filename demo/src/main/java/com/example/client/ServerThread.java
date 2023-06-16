package com.example.client;


import com.example.demo.Dot;
import com.example.demo.HelloApplication;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.function.Consumer;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean running;
    private Consumer<Dot> dots;
    public ServerThread(String address, int port) {
        try {
            socket = new Socket(address, port);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            String message;
            while ((message = reader.readLine()) != null){
                dots.accept(Dot.fromMessage(message));
            }
        } catch (IOException e) {
            System.out.println("dot accept");
            throw new RuntimeException(e);
        }
    }

    public void send(double centerX, double centerY,  Color myColor, double radius) {
        writer.println(Dot.toMessage(centerX, centerY, myColor, radius));
    }
}

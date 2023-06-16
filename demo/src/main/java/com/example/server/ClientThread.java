package com.example.server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    public Socket getSocket() {
        return socket;
    }

    private Socket socket;
    private PrintWriter writer;
    private Server server;
    private BufferedReader reader;
    private String clientName = null;

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try {
            InputStream input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            this.reader = new BufferedReader(new InputStreamReader(input));
            this.writer = new PrintWriter(output, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        try {
            String message;
            while ((message = reader.readLine()) != null){
                server.broadcast(this, message);
                System.out.println(message);
            }
            System.out.println("closed");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String message){
        writer.println(message);
    }
}


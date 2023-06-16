package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        while(true){
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                ClientThread thread = new ClientThread(clientSocket, this);
                clients.add(thread);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void broadcast(ClientThread sender, String message){
        for(var currentClient : clients){
            currentClient.send(message);
        }

    }
}

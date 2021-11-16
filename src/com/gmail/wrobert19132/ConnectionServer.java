package com.gmail.wrobert19132;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;


public class ConnectionServer extends Thread{
    private final Integer port;
    private Boolean closing = false;

    ArrayList<ClientConnection> connectedClients = new ArrayList<>();

    public ConnectionServer(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket listeningSocket = new ServerSocket(port);
            System.out.println("server started!");
            while (!closing) {
                Socket connection = listeningSocket.accept();
                System.out.println("New connection at "+connection.toString());
                ClientConnection newUser = new ClientConnection(this, connection);
                this.connectedClients.add(newUser);
                newUser.start();
            }
            listeningSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.closing = true;
    }

    public void broadcast(String content, ClientConnection exclude) {
        System.out.println("Broadcasting message \""+ content + "\"");
        for (ClientConnection client: connectedClients) {
            if (client.isAlive()) {
                if (client != exclude) {
                    client.sendClient(content);
                }
            }

        }
    }
}

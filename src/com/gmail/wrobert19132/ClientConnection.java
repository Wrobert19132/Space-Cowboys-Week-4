package com.gmail.wrobert19132;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread{
    private final ConnectionServer server;
    private final Socket socket;
    private PrintWriter writer;

    Boolean close = false;

    public ClientConnection(ConnectionServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(),true);

            String username = reader.readLine();
            sendClient("Connection Successful! Welcome!");

            String newMessage;
            while (!close) {
                newMessage = reader.readLine();
                if (newMessage != null) {
                    this.processMessage("[" + username + "] " + newMessage);
                } else {
                    close = true;
                }

            }
        } catch (IOException e) {
            System.out.println("Fatal I/O error has occurred!");
            e.printStackTrace();
        }
    }

    public void processMessage(String content) {
        server.broadcast(content, this);
    }

    public void sendClient(String content) {
        writer.println(content);
    }
}

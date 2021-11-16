package com.gmail.wrobert19132;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;


public class Client {
    private Socket socket;

    private final String address;
    private final Integer port;

    public Client(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public void go() {
        try {
            socket = new Socket(address, port);

            ClientMessageListener messageListener = new ClientMessageListener(socket);
            ClientMessageSender messageSender = new ClientMessageSender(socket);

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String username = "";
            while (username.equals("") || username.length() > 8) {
                System.out.println("Enter a username;");
                username = console.readLine();
            }
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(username);

            messageSender.start();
            messageListener.start();

        } catch (IOException e) {
            System.out.println("Connection Error!");
            e.printStackTrace();
        }
    }
}

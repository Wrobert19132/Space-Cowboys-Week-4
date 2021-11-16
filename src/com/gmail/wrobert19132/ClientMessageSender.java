package com.gmail.wrobert19132;

import java.io.*;
import java.net.Socket;

public class ClientMessageSender extends Thread{
    private final Socket socket;
    private Boolean done = false;

    ClientMessageSender(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (!done) {
                writer.println(consoleReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("Critical network error!");
            e.printStackTrace();
        }

    }
}

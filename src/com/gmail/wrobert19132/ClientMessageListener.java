package com.gmail.wrobert19132;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageListener extends Thread{
    Socket socket;
    Boolean done = false;

    ClientMessageListener(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!done) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println("Critical network error!");
            e.printStackTrace();
        }

    }
}

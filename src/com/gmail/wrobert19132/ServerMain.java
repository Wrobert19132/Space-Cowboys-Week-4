package com.gmail.wrobert19132;

public class ServerMain {

    public static void main(String[] args) {
        ConnectionServer server = new ConnectionServer(12344);
        server.start();
    }
}

package com.gmail.wrobert19132;

import java.net.SocketAddress;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client("localhost", 12344);
        client.go();
    }
}

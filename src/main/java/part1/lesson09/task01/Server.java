package part1.lesson09.task01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static ServerSocket serverSocket;
    public LinkedList<ClientConnection> clientList = new LinkedList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket(10000, 50, InetAddress.getLocalHost());
            new Thread(() -> {
                try {
                    listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            clientList.add(new ClientConnection(socket, clientList));
            System.out.println("Client added");
        }
    }
}

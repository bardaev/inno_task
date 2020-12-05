package part1.lesson08.task02.server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 */

public class ClientConnection extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private LinkedList<ClientConnection> clientList;

    ClientConnection(Socket socket, LinkedList<ClientConnection> clientList) throws IOException {
        this.socket = socket;
        this.clientList = clientList;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String message;

        try {
            while (true) {
                message = in.readLine();
                for (ClientConnection client : clientList) {
                    client.sendMsg(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                downService();
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }

    private void sendMsg(String msg) throws IOException {
        try {
            out.write(msg);
            out.flush();
        } catch (NullPointerException e) {
            downService();
        }
    }

    private void downService() throws IOException {
        if (!socket.isClosed()) {
            in.close();
            out.close();
            for (ClientConnection client : clientList) {
                if (client.equals(this)) client.interrupt();
                clientList.remove(this);
                break;
            }
        }
    }
}

package part1.lesson08.task02.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client controller receive and send messages.
 * Also this class connects to server
 */

public class ClientController {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Window window;
    private String nicName;

    public ClientController(Window window) {
        this.window = window;
    }

    /**
     * Connect to server
     * @param nicName - string which identifies Client unit
     * @return
     */
    public boolean connect(String nicName) {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), 10000);
            this.nicName = nicName;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            new ReadMsg().start();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void sendMsg(String str) {
        try {
            out.write(nicName + ":" + str + "\n");
            out.flush();
        } catch (IOException e) {
            downService();
        }
    }

    private void downService() {
        try {
            if (!clientSocket.isClosed()) {
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            window.displayError("Ошибка отправки сообщения");
        }
    }

    /**
     * ReadMsg class listen server on a separate thread
     */

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            try {
                while (true) {
                    while (in.ready()) {
                        sb.append((char) in.read());
                    }
                    if (sb.length() > 0) {
                        window.displayMsg(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            } catch (IOException e) {
                downService();
                window.displayError("Ошибка приема сообщения");
            }
        }
    }
}

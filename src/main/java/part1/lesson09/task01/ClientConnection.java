package part1.lesson09.task01;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;

public class ClientConnection extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private LinkedList<ClientConnection> clientList;

    private final String METHOD = "GET";
    private final String SEPARATOR = "/";
    private final String PROTOCOL = "HTTP";
    private final String HTTP_VERSION = "1.1";

    ClientConnection(Socket socket, LinkedList<ClientConnection> clientList) throws IOException {
        this.socket = socket;
        this.clientList = clientList;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {

        try {
            while (true) {
                if (in.ready()) {
                    int c;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((c = in.read()) != -1 && in.ready()) {
                        stringBuilder.append((char) c);
                    }
                    if (isGetRequest(stringBuilder.toString())) printCurrentDir();
                    else printError();
                    downService();
                }
            }
        } catch (IOException e) {
            System.err.println("Stream closed");
            try {
                downService();
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }

    private boolean isGetRequest(String request) {
        String[] r = request.split(" ");
        if (r[0].equals(METHOD) && r[1].startsWith(SEPARATOR) && r[2].equals(PROTOCOL + SEPARATOR + HTTP_VERSION)) {
            return true;
        }
        return false;
    }

    private void printCurrentDir() throws IOException {
        File file = new File(System.getProperty("user.dir"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200 OK\n");
        Arrays.stream(file.list()).forEach(s -> stringBuilder.append(s + "\n"));
        sendMsg(stringBuilder.toString());
    }

    private void printError() throws IOException {
        sendMsg("HTTP1.1/ 404 Not Found");
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
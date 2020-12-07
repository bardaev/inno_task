package part1.lesson09.task01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunMe {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
        Socket socket = new Socket(InetAddress.getLocalHost(), 10000);
        System.out.println(socket.isConnected());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Thread.sleep(1000);
        out.write("GET / HTTP/1.1 ");
        out.flush();
        while (true) {
            if (in.ready()) {
                in.lines().forEach(System.out::println);
            }
        }
    }
}

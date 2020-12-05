package part1.lesson08.task02;

import part1.lesson08.task02.client.ChatWindow;
import part1.lesson08.task02.server.Server;

public class RunMe {
    public static void main(String[] args) {
        new Server();
        new ChatWindow();
        new ChatWindow();
    }
}

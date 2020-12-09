package part1.lesson10.task01;

import part1.lesson10.task01.game.multithread.MultiThreadController;
import part1.lesson10.task01.game.onethread.Controller;

public class RunMe {
    public static void main(String[] args) {
        new Controller("src/main/java/part1/lesson10/task01/config/config.json", false);
        new MultiThreadController("src/main/java/part1/lesson10/task01/config/config.json");

    }
}

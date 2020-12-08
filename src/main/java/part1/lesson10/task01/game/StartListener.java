package part1.lesson10.task01.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StartListener implements ActionListener {

    private Controller controller;

    public StartListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.start();
    }
}

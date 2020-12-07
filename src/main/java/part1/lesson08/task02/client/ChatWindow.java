package part1.lesson08.task02.client;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ChatWindow class represents graphical window.
 * This window have text area and input text
 */

public class ChatWindow extends Frame implements Window {
    TextArea chatMsg;
    TextField inputMsg;
    NicNameDialog dc;
    ClientController clientController;

    public ChatWindow() {
        initGUI();
    }

    private void initGUI() {
        this.setSize(700, 600);
        this.setVisible(true);
        this.setResizable(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        chatMsg = new TextArea("",32, 85, TextArea.SCROLLBARS_VERTICAL_ONLY);
        chatMsg.setEditable(false);
        add(chatMsg);
        inputMsg = new TextField(85);
        add(inputMsg);
        repaint();
        clientController = new ClientController(this);
        dc = new NicNameDialog(this, clientController);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        inputMsg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) clientController.sendMsg(inputMsg.getText());;
                inputMsg.setText(null);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * Displays incoming message
     * @param msg - incoming message
     */
    public void displayMsg(String msg) {
        chatMsg.append(msg);
        chatMsg.append("\n");
        repaint();
    }

    /**
     * Displays message error in new dialog window
     * @param msg
     */

    public void displayError(String msg) {
        new ErrorDialog(ChatWindow.this, msg);
    }
}

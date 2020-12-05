package part1.lesson08.task02.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorDialog extends Dialog {
    Label label;
    Button close;

    ErrorDialog(Dialog d, String msg) {
        super(d);
        initDialog(msg);
    }

    ErrorDialog(Frame f, String msg) {
        super(f);
        initDialog(msg);
    }

    private void initDialog(String msg) {
        setLayout(new BorderLayout());
        Panel panel = new Panel();

        label = new Label(msg);

        close = new Button("Close");

        panel.add(label);

        panel.add(close);

        add("Center", panel);
        setSize(200, 100);
        setVisible(true);
        setResizable(false);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

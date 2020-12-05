package part1.lesson08.task02.client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NicNameDialog extends Dialog {
    Label labelNic;
    Button enter;
    TextField fieldNic;
    ClientController clientController;

    public NicNameDialog(Frame parent, ClientController clientController) {
        super(parent, false);
        this.clientController = clientController;
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        Panel panel = new Panel();

        labelNic = new Label("NicName");
        fieldNic = new TextField(20);
        enter = new Button("Подключиться");

        panel.add(labelNic);
        panel.add(fieldNic);

        panel.add(enter);

        add("Center", panel);
        setSize(180,150);
        setVisible(true);
        enter.addActionListener(e -> {
            String nic = fieldNic.getText();

            if (clientController.connect(nic)) {
                dispose();
            } else {
                new ErrorDialog(NicNameDialog.this, "Connection Error");
            }

        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        repaint();
    }
}

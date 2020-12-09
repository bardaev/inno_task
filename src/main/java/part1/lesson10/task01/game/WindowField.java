package part1.lesson10.task01.game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowField extends Frame {

    private Button[][] field;
    static final public Color BLACK = Color.BLACK;
    static final public Color WHITE = Color.WHITE;
    private ControllerInterface controller;

    public WindowField(int rows, int cols, ControllerInterface controller, String name) {
        super(name);

        this.controller = controller;

        Dimension dimension = getDimension(rows, cols);
        setSize(dimension.width, dimension.height);
        setResizable(false);
        setLocationRelativeTo(null);

        GridLayout gridLayout = new GridLayout(rows, cols, 0, 0);
        setLayout(gridLayout);

        field = new Button[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button button = new Button();
                button.addActionListener(new StartListener(controller));
                field[i][j] = button;
                add(button);
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private Dimension getDimension(int rows, int cols) {

        final int widthCell = 30;
        final int heightCell = 30;

        Dimension dimension = new Dimension();

        int w = widthCell * cols;
        int h = heightCell * rows;

        dimension.setSize(w, h);
        return dimension;
    }

    public void setColor(int row, int col, Color color) {
        field[row][col].setBackground(color);
    }
}

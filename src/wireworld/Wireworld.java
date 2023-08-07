package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import javax.swing.*;
import java.awt.*;

public class Wireworld extends JFrame {
    private JPanel WireworldGUI;
    private JPanel WireworldPanel;
    private JButton button1;
    private JButton button2;
    private Matrix cells;

    private final int CELL_NUM = 30;
    private final int CELL_SIZE = 20;

    public Wireworld() {
        setContentPane(WireworldGUI);

        WireworldPanel.removeAll();
        WireworldPanel.setLayout(new GridLayout(CELL_NUM, CELL_NUM));
        Cell c;
        cells = new Matrix(CELL_NUM, CELL_NUM);
        for (int i = 0; i < CELL_NUM * CELL_NUM; i++) {
            c = new Cell();
            c.setPreferredSize(new Dimension(10, 10));
            cells.setElement(i, c);
            WireworldPanel.add(c);
        }
        WireworldPanel.revalidate();

        setTitle("Wireworld Cellular Automaton");
//        setMinimumSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(CELL_SIZE * CELL_NUM, CELL_SIZE * CELL_NUM));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

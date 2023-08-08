package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wireworld extends JFrame {
    private JPanel WireworldGUI;
    private JPanel WireworldPanel;
    private JButton RunButton;
    private JButton button2;
    private JButton NextGenButton;
    private Matrix cells;

    public Wireworld() {
        setContentPane(WireworldGUI);

        Timer timer = new Timer(Const.GENERATION_SPEED, e -> computeNextGeneration());
        RunButton.addActionListener(e -> timer.start());
        NextGenButton.addActionListener(e -> computeNextGeneration());

        WireworldPanel.setLayout(new GridLayout(Const.CELLS_X, Const.CELLS_Y));
        cells = new Matrix(Const.CELLS_X, Const.CELLS_Y);
        for (int i = 0; i < Const.CELLS_X; i++) {
            for (int j = 0; j < Const.CELLS_Y; j++) {
                Cell c = new Cell(i, j);
                cells.setElement(i, j, c);
                WireworldPanel.add(c);
            }
        }
        WireworldPanel.revalidate();

        setTitle("Wireworld Cellular Automaton");
        setMinimumSize(new Dimension(Const.CELL_SIZE * Const.CELLS_X, Const.CELL_SIZE * Const.CELLS_Y));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void computeNextGeneration() {
        for (int i = 0; i < Const.CELLS_X * Const.CELLS_Y; i++) {
            ((Cell)cells.getElement(i)).computeNextState(cells);
        }

        for (int i = 0; i < Const.CELLS_X * Const.CELLS_Y; i++) {
            ((Cell)cells.getElement(i)).setState(((Cell)cells.getElement(i)).getStateToChangeIndex());
        }
    }
}

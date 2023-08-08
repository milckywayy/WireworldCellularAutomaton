package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import javax.swing.*;
import java.awt.*;

public class Wireworld extends JFrame {
    private JPanel wireworldGUI;
    private JPanel wireworldPanel;
    private JPanel menuPanel;
    private JButton runButton;
    private JButton nextGenButton;
    private JButton chooseReadFileButton;
    private JButton chooseExportFileButton;
    private final Matrix cells;
    private final Timer timer;

    public Wireworld() {
        setContentPane(wireworldGUI);

        timer = new Timer(Const.GENERATION_SPEED, e -> computeNextGeneration());
        runButton.addActionListener(e -> runAutomaton());
        nextGenButton.addActionListener(e -> computeNextGeneration());

        wireworldPanel.setLayout(new GridLayout(Const.CELLS_Y, Const.CELLS_X));
        cells = new Matrix(Const.CELLS_Y, Const.CELLS_X);
        for (int y = 0; y < Const.CELLS_Y; y++) {
            for (int x = 0; x < Const.CELLS_X; x++) {
                Cell c = new Cell(y, x);
                cells.setElement(y, x, c);
                wireworldPanel.add(c);
            }
        }
        wireworldPanel.revalidate();

        setTitle("Wireworld Cellular Automaton");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        int cellSize = menuPanel.getWidth() / Const.CELLS_X;
        wireworldPanel.setPreferredSize(new Dimension(cellSize * Const.CELLS_X, cellSize * Const.CELLS_Y));
        pack();
    }

    public void runAutomaton() {
        if (timer.isRunning()) {
            runButton.setText("Run");
            timer.stop();
        }
        else {
            runButton.setText("Stop");
            timer.start();
        }
    }

    public void computeNextGeneration() {
        for (int i = 0; i < Const.CELLS_Y * Const.CELLS_X; i++) {
            ((Cell) cells.getElement(i)).computeNextState(cells);
        }

        for (int i = 0; i < Const.CELLS_Y * Const.CELLS_X; i++) {
            ((Cell) cells.getElement(i)).setState(((Cell) cells.getElement(i)).getStateToChangeIndex());
        }
    }
}

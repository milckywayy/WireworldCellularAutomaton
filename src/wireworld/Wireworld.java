package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Wireworld extends JFrame {
    private JPanel wireworldGUI;
    private JPanel wireworldPanel;
    private JPanel menuPanel;
    private JButton runButton;
    private JButton nextGenButton;
    private JButton chooseReadFileButton;
    private JButton chooseExportFileButton;
    private final Matrix cells;
    private final WireworldReader reader;
    private final Timer timer;
    JFileChooser fileChooser;

    public Wireworld() {
        setContentPane(wireworldGUI);

        timer = new Timer(Const.GENERATION_SPEED, e -> computeNextGeneration());
        runButton.addActionListener(e -> runAutomaton());
        nextGenButton.addActionListener(e -> computeNextGeneration());
        chooseReadFileButton.addActionListener(e -> ReadWireworldFromFile());

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

        reader = new WireworldReader();
        fileChooser = new JFileChooser();
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

    private String showFileDialog() {

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }

        return null;
    }

    public void ReadWireworldFromFile() {
        Matrix wireworld;

        try {
            wireworld = reader.readWireworldFromFile(showFileDialog());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch (NullPointerException e) {
            return;
        }

        if (wireworld.getM() != Const.CELLS_Y || wireworld.getN() != Const.CELLS_X) {
            JOptionPane.showMessageDialog(this, "Invalid Wireworld size.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < wireworld.size(); i++) {
            if (((Integer)wireworld.getElement(i)) < 0 || ((Integer)wireworld.getElement(i)) >= Const.CELLS_STATES_NUM) {
                JOptionPane.showMessageDialog(this, "Wireworld contains unsupported cells.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        for (int i = 0; i < wireworld.size(); i++) {
            ((Cell)cells.getElement(i)).setState((Integer)wireworld.getElement(i));
        }
    }
}

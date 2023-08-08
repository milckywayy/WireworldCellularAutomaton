package wireworld.cell;

import matrix.Matrix;

import java.awt.*;

public class CellElectronHead implements CellState {
    private final Color BACKGROUND_COLOR = new Color(0, 0, 255);

    public CellElectronHead() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
        cell.setStateToChangeIndex(3);
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

package wireworld.cell;

import matrix.Matrix;

import java.awt.*;

public class CellElectronTail implements CellState {
    private final Color BACKGROUND_COLOR = new Color(255, 0, 0);

    public CellElectronTail() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
        cell.setStateToChangeIndex(1);
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

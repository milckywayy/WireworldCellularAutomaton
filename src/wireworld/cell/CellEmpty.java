package wireworld.cell;

import matrix.Matrix;

import java.awt.*;
import java.util.ArrayList;

public class CellEmpty implements CellState {
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);

    public CellEmpty() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

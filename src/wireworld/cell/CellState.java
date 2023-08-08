package wireworld.cell;

import matrix.Matrix;

import java.awt.*;

public interface CellState {
    void computeNextState(Cell cell, Matrix cells);

    Color getColor();
}

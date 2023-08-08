package wireworld;

import matrix.Matrix;
import wireworld.cell.Cell;

import java.awt.*;

public interface CellInterface {
    public void computeNextState(Cell cell, Matrix cells);

    public Color getColor();
}

package wireworld.cell;

import matrix.Matrix;

import java.awt.*;
import java.util.ArrayList;

public class CellConductor implements CellState {
    private final Color BACKGROUND_COLOR = new Color(255, 255, 0);

    public CellConductor() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
        ArrayList<Integer> neighbours = cell.countNeighbours(cells);

        if (neighbours.get(2) == 1 || neighbours.get(2) == 2) {
            cell.setStateToChangeIndex(2);
        }
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

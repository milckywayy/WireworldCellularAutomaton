package wireworld.cell;

import matrix.Matrix;
import wireworld.CellInterface;

import java.awt.*;
import java.util.ArrayList;

public class CellAlive implements CellInterface {
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);

    public CellAlive() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
        ArrayList<Integer> neighbours = cell.countNeighbours(cells);

        if (neighbours.get(1) < 2) {
            cell.setStateToChangeIndex(0);
        }
        else if (neighbours.get(1) > 3) {
            cell.setStateToChangeIndex(0);
        }
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

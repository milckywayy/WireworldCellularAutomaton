package wireworld.cell;

import matrix.Matrix;
import wireworld.CellInterface;

import java.awt.*;
import java.util.ArrayList;

public class CellDead implements CellInterface {
    private final Color BACKGROUND_COLOR = new Color(255, 255, 255);

    public CellDead() {
    }

    public void computeNextState(Cell cell, Matrix cells) {
        ArrayList<Integer> neighbours = cell.countNeighbours(cells);

        if (neighbours.get(1) == 3) {
            cell.setStateToChangeIndex(1);
        }
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

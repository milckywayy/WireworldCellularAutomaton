package wireworld.cell;

import matrix.Matrix;
import wireworld.CellInterface;
import wireworld.Const;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell extends JButton {
    private int stateIndex = 0;
    private int stateToChangeIndex = 0;
    private final List<Object> states = new ArrayList<>(Arrays.asList(new CellDead(), new CellAlive()));
    private CellInterface state = (CellInterface) states.get(stateIndex);

    private final int x;
    private final int y;

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;

        setFocusable(false);

        setBackground(state.getColor());
        setBorder(BorderFactory.createLineBorder(Const.CELL_MARGIN_COLOR, Const.CELL_MARGIN_SIZE));

        super.addActionListener(e -> clickHandle());
    }

    public int getState() {
        return stateIndex;
    }

    public int getStateToChangeIndex() {
        return stateToChangeIndex;
    }

    public void setState(int stateIndex) {
        this.stateIndex = stateIndex;
        stateToChangeIndex = stateIndex;
        state = (CellInterface) states.get(this.stateIndex);
        setBackground(state.getColor());
    }

    public void setStateToChangeIndex(int index) {
        stateToChangeIndex = index;
    }

    public void computeNextState(Matrix cells) {
        state.computeNextState(this, cells);
    }

    public ArrayList<Integer> countNeighbours(Matrix cells) {
        ArrayList<Integer> neighbours = new ArrayList<>(Arrays.asList(0, 0));
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int y = (this.y + j + Const.CELLS_Y) % Const.CELLS_Y;
                int x = (this.x + i + Const.CELLS_X) % Const.CELLS_X;

                if (y == this.y && x == this.x) {
                    continue;
                }

                int cell_state = ((Cell)cells.getElement(y, x)).getState();
                neighbours.set(cell_state, neighbours.get(cell_state) + 1);
            }
        }

        return neighbours;
    }

    private void clickHandle() {
        stateIndex++;

        if (stateIndex >= states.size()) {
            stateIndex = 0;
        }

        setState(stateIndex);
    }
}

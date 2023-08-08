package wireworld.cell;

import wireworld.CellInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell extends JButton {
    private final int MARGIN_SIZE = 1;
    private final Color MARGIN_COLOR = new Color(198, 206, 208, 255);

    private int stateIndex = 0;
    private List<Object> states = new ArrayList<>(Arrays.asList(new CellDead(), new CellAlive()));
    private CellInterface state = (CellInterface) states.get(stateIndex);

    public Cell() {
        setFocusable(false);

        setBackground(state.getColor());
        setBorder(BorderFactory.createLineBorder(MARGIN_COLOR, MARGIN_SIZE));

        super.addActionListener(e -> clickHandle());
    }

    public void setState(int stateIndex) {
        this.stateIndex = stateIndex;
        this.state = (CellInterface) states.get(this.stateIndex);
        setBackground(state.getColor());
    }

    private void clickHandle() {
        stateIndex++;

        if (stateIndex >= states.size()) {
            stateIndex = 0;
        }

        setState(stateIndex);
    }
}

package wireworld.cell;

import wireworld.CellInterface;

import java.awt.*;

public class CellAlive implements CellInterface {
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);

    public CellAlive() {
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

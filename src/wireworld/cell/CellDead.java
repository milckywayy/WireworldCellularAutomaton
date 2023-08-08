package wireworld.cell;

import wireworld.CellInterface;

import java.awt.*;

public class CellDead implements CellInterface {
    private final Color BACKGROUND_COLOR = new Color(255, 255, 255);

    public CellDead() {
    }

    public Color getColor() {
        return BACKGROUND_COLOR;
    }
}

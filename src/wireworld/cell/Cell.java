package wireworld.cell;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private final int MARGIN_SIZE = 1;
    private final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private final Color MARGIN_COLOR = new Color(198, 206, 208, 255);

    public Cell() {
        setFocusable(false);

        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(MARGIN_COLOR, MARGIN_SIZE));

        super.addActionListener(e -> clickHandle());
    }

    private void clickHandle() {
        System.out.println("lula");
    }
}

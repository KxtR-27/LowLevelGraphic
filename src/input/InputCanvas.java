package input;

import javax.swing.*;
import java.awt.*;

public class InputCanvas extends JPanel {
    private Color bgColor = Color.WHITE;

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Dimension windowSize = getBounds().getSize();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, windowSize.width, windowSize.height);

        repaint();
    }
}
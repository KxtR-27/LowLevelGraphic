package player;

import javax.swing.*;
import java.awt.*;

public class PlayerCanvas extends JPanel {
    private final Player player = new Player();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Dimension windowSize = getBounds().getSize();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, windowSize.width, windowSize.height);

        player.tick();
        player.draw(g2);

        repaint();
    }
}

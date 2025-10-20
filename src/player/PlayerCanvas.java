package player;

import javax.swing.*;
import java.awt.*;

public class PlayerCanvas extends JPanel {
    private final Player player = new Player();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double scale = 0.25;
        g2.scale(scale, scale);

        // draw background
        Dimension windowSize = getBounds().getSize();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, (int) (windowSize.width / scale), (int) (windowSize.height / scale));

        // draw player
        player.tick();
        player.draw(g2);

        repaint();
    }
}
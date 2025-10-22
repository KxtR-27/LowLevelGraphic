package player;

import javax.swing.*;
import java.awt.*;

public class PlayerCanvas extends JPanel {
    private Player player;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double scale = 0.05;
        g2.scale(scale, scale);

        // draw background
        Dimension windowSize = getBounds().getSize();
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, (int) (windowSize.width / scale), (int) (windowSize.height / scale));

        // draw player
        if (player != null) {
            player.tick();
            player.draw(g2);
        }

        repaint();
    }

    public void addPlayer(Player player, Component parent) {
        this.player = player;
        player.connectControllerTo(parent);
    }
}
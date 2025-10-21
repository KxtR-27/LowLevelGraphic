package input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputCanvas extends JPanel implements KeyListener {
    private Color bgColor = Color.WHITE;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Dimension windowSize = getBounds().getSize();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, windowSize.width, windowSize.height);

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("Key Typed: %c%n", e.getKeyChar());

        this.bgColor = (switch (e.getKeyChar()) {
            case 'r' -> Color.RED;
            case 'a' -> Color.ORANGE;
            case 'i' -> Color.YELLOW;
            case 'n' -> Color.GREEN;
            case 'b' -> Color.CYAN;
            case 'o' -> Color.BLUE;
            case 'w' -> Color.MAGENTA;

            default -> Color.BLACK;
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
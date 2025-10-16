import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class LLGCanvas extends JPanel {
    private static final Color[] PALETTE = {
            (new Color(212, 210, 155)), // "dark vanilla"
            (new Color(120, 164, 106)), // "asparagus"
            (new Color(94, 133, 73)),   // "fern green"
            (new Color(88, 68, 34)),    // "nut brown"
            (new Color(0, 0, 0, 0))     // transparent
    };
    private static final int[][] PIXEL_COLORS = {
            // 11 columns, 16 rows
            {4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 4},
            {4, 3, 0, 0, 0, 1, 0, 1, 1, 3, 4},
            {3, 0, 1, 1, 1, 1, 1, 3, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 3, 0, 3, 1, 3},
            {3, 2, 2, 2, 1, 1, 3, 0, 3, 2, 3},
            {3, 3, 3, 0, 2, 2, 2, 0, 2, 2, 3},
            {3, 1, 3, 0, 3, 3, 0, 0, 2, 2, 3},
            {3, 0, 0, 0, 1, 3, 0, 0, 3, 2, 3},
            {4, 3, 0, 0, 0, 0, 0, 0, 3, 2, 3},
            {3, 0, 3, 3, 3, 2, 2, 3, 3, 3, 4},
            {3, 0, 3, 1, 2, 2, 2, 3, 0, 0, 3},
            {4, 3, 3, 1, 2, 2, 2, 3, 0, 0, 3},
            {4, 4, 3, 1, 2, 2, 2, 2, 3, 3, 4},
            {4, 4, 3, 3, 3, 3, 2, 2, 3, 4, 4},
            {4, 4, 3, 3, 4, 4, 3, 3, 3, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4}
    };
    private static final int PIXEL_SIZE = 24;

    private final boolean draw_squares;

    public LLGCanvas(boolean draw_squares) {
        super();
        this.draw_squares = draw_squares;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Set 2D context
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform t = g2.getTransform();

        // row determines y-value
        for (int y = 0; y < PIXEL_COLORS.length; y++) {
            g2.setTransform(t);
            g2.translate(0, y * PIXEL_SIZE);

            // column determines x-value
            for (int x = 0; x < PIXEL_COLORS[y].length; x++) {
                Color color = PALETTE[PIXEL_COLORS[y][x]];
                g2.setColor(color);

                if (draw_squares)
                    g2.fillRect(0, 0, PIXEL_SIZE, PIXEL_SIZE);
                else
                    g2.fillOval(0, 0, PIXEL_SIZE, PIXEL_SIZE);

                g2.translate(PIXEL_SIZE, 0);
            }
        }

        repaint();
    }
}

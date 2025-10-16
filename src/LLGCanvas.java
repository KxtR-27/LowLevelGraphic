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
    private static final int[][] PIXEL_COLOR_MAP = {
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
    private static final int PIXEL_SIZE = 8;

    private boolean drawSquares;

    @Override
    public void paintComponent(Graphics g) {
        // Set 2D context
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform reset = new AffineTransform(AffineTransform.getRotateInstance(AffineTransform.TYPE_IDENTITY));

        // draw squares
        drawSquares = true;
        drawPixels(g2);

        // translate, then draw circles
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length + 4), 0);
        drawSquares = false;
        drawPixels(g2);

        // translate, flip upside down, then draw squares
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length * 2 + 4), PIXEL_SIZE * (PIXEL_COLOR_MAP.length));
        g2.rotate(Math.PI);
        drawSquares = true;
        drawPixels(g2);

        // translate while upside down, then draw circles
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length + 4) * -1, 0);
        drawSquares = false;
        drawPixels(g2);

        // reset transform, scale 2x, translate, then draw squares
        g2.setTransform(reset);
        g2.scale(2, 2);
        g2.translate(0, PIXEL_SIZE * PIXEL_COLOR_MAP.length);
        // draw squares
        drawSquares = true;
        drawPixels(g2);

        // translate, then draw circles
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length + 4), 0);
        drawSquares = false;
        drawPixels(g2);

        // translate, rotate, scale down, then draw squares
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length + 4) * 1.5, 0);
        g2.scale(0.33, 0.33);
        g2.rotate(Math.PI / 2.75);
        drawSquares = true;
        drawPixels(g2);

        // translate, then draw circles
        g2.translate(PIXEL_SIZE * (PIXEL_COLOR_MAP[0].length + 4), 0);
        drawSquares = false;
        drawPixels(g2);

        repaint();
    }

    private void drawPixels(Graphics2D g2) {
        AffineTransform t = g2.getTransform();
        // row determines y-value
        for (int y = 0; y < PIXEL_COLOR_MAP.length; y++) {
            g2.setTransform(t);
            g2.translate(0, y * PIXEL_SIZE);

            // column determines x-value
            for (int x = 0; x < PIXEL_COLOR_MAP[y].length; x++) {
                Color color = PALETTE[PIXEL_COLOR_MAP[y][x]];
                g2.setColor(color);

                if (drawSquares)
                    g2.fillRect(0, 0, PIXEL_SIZE, PIXEL_SIZE);
                else
                    g2.fillOval(0, 0, PIXEL_SIZE, PIXEL_SIZE);

                g2.translate(PIXEL_SIZE, 0);
            }
        }

        g2.setTransform(t);
    }
}

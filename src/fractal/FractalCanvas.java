package fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.awt.Color.*;
import static java.awt.Color.BLUE;
import static java.awt.Color.CYAN;
import static java.awt.Color.GREEN;
import static java.awt.Color.MAGENTA;

public class FractalCanvas extends JPanel {
    private Graphics2D context;

    double scale = 20.0;
    double scaleFactor = 0.99;

    double rotationRad = 0.0;
    double rotationFactor = Math.PI / 40;

    int framesBetweenDraws = 50;
    int currentFramesBetween = 1;

    private final Polygon triangle = new Polygon(
            new int[]{-15, 0, 15},
            new int[]{-15, 10, -15},
            3
    );

    private final Color[] colors = { RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, MAGENTA };
    private int colorIndex = 0;

    @Override
    protected void paintComponent(Graphics g) {
        context = (Graphics2D) g;
        AffineTransform backup = context.getTransform();

        if (++currentFramesBetween % framesBetweenDraws == 0) {
            currentFramesBetween = 0;

            transformFractal();
            drawFractal();

            context.setTransform(backup);
        }

        repaint();
    }

    private void drawFractal() {
        context.setColor(colors[colorIndex++]);
        colorIndex %= colors.length;

        context.drawPolygon(triangle);
    }

    private void transformFractal() {
        Dimension windowSize = this.getBounds().getSize();
        AffineTransform transform = new AffineTransform();
        Point translationOffset = new Point(75, 100);

        int translateX = (int) (windowSize.width / 2.0) + translationOffset.x;
        int translateY = (int) (windowSize.height / 2.0) + translationOffset.y;
        transform.translate(translateX, translateY);

        transform.scale(scale, scale);
        scale *= scaleFactor;

        transform.rotate(rotationRad);
        rotationRad += rotationFactor;

        context.setTransform(transform);
    }
}

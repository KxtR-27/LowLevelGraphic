package motion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MotionCanvas extends JPanel {
    // Spritesheet animation in question has 4 frames, all 16x16
    private final BufferedImage spritesheet;
    private final Dimension frameSize;
    private final Point[] framePoints;

    // "sprite" frame logic
    private int timesPaintedThisFrame = 0;
    private int currentFrame = 0;
    private double x = 0;

    public MotionCanvas() {
        spritesheet = safelyBufferSpritesheet();

        frameSize = new Dimension(16, 16);
        framePoints = new Point[]{
                new Point(0, 32),
                new Point(16, 32),
                new Point(32, 32),
                new Point(48, 32)
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);

        // draw background
        Dimension windowSize = getBounds().getSize();
        g2.setColor(g2.getBackground());
        g2.fillRect(0, 0, windowSize.width, windowSize.height);

        // when to switch sprite frames
        int paintCallsBeforeSwitchingFrames = 120;
        timesPaintedThisFrame = ++timesPaintedThisFrame % paintCallsBeforeSwitchingFrames;
        if (timesPaintedThisFrame == 0) currentFrame = ++currentFrame % framePoints.length;

        // "sprite" "move speed"
        double dx = 0.1;
        x = x + dx;
        if (x >= windowSize.width) x = -100;

        // "sprite" "location" and "size"
        g2.translate(x, 128);
        double scale = 4.0;
        g2.scale(scale, scale);

        // draw frame from image
        Point currentPoint = framePoints[currentFrame];
        g2.drawImage(
                spritesheet,
                0, 0,
                    frameSize.width, frameSize.height,

                currentPoint.x, currentPoint.y,
                currentPoint.x + frameSize.width, currentPoint.y + frameSize.height,
                null
        );

        repaint();
    }

    /** Wraps the image buffering process with error handling */
    private static BufferedImage safelyBufferSpritesheet() {
        BufferedImage image;

        try {
            image = ImageIO.read(new File("src/motion/elf_spritesheet.png"));
        }
        catch (IllegalArgumentException fileIsNullException) {
            System.err.printf("Supplied null file to MotionCanvas object.%n");
            throw new RuntimeException(fileIsNullException);
        }
        catch (IOException errorDuringBufferingException) {
            System.err.printf("Something went wrong when buffering the image.%n");
            throw new RuntimeException(errorDuringBufferingException);
        }

        return image;
    }
}

package player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Tickable {
    private final BufferedImage spritesheet;
    private final Dimension frameSize;
    private final int hFrames;
//    private final int vFrames; // The math says it's arbitrary, I guess...

    private final double fps;
    private double secondsAccumulatedBeforeNextFrame;

    private Point currentFrameRange;

    private final Point idleFrameRange = new Point(0, 3);
    private final Point walkFrameRange = new Point(14, 18);

    private int currentFrame;


    public Sprite() {
        spritesheet = safelyBufferSpritesheet();
        frameSize = new Dimension(16, 16);
        hFrames = 7;
//        vFrames = 8; // The math says it's arbitrary, I guess...

        fps = 8.0;

        currentFrameRange = idleFrameRange;
        currentFrame = currentFrameRange.x;
    }


    public void onTick(double deltaTime) {
        secondsAccumulatedBeforeNextFrame += deltaTime;

        double fpsPeriod = 1.0 / fps;
        while (secondsAccumulatedBeforeNextFrame / fpsPeriod >= 1) {
            currentFrame++;
            secondsAccumulatedBeforeNextFrame -= fpsPeriod;
        }

        if (currentFrame >= currentFrameRange.y || currentFrame < currentFrameRange.x)
            currentFrame = currentFrameRange.x;
    }


    public void draw(Graphics2D g2, Point location) {
        Point currentFramePoint = new Point(
                (currentFrame % hFrames) * frameSize.width,  // if hFrames = 8, frame "3" would be in first row
                (currentFrame / hFrames) * frameSize.height     // if hFrames = 8, frame "8" would be in the start of second row
        );

        // Okay, I know this works. It's the above and the tick that get quirky
        g2.drawImage(
                spritesheet,

                location.x,
                location.y,
                location.x + frameSize.width,
                location.y + frameSize.height,

                currentFramePoint.x,
                currentFramePoint.y,
                currentFramePoint.x + frameSize.width,
                currentFramePoint.y + frameSize.height,

                null
        );
    }


    public void changeAnimationIfNeeded(Point delta) {
        boolean moving = delta.x != 0 || delta.y != 0;

        if (!moving && currentFrameRange != idleFrameRange)
            currentFrameRange = idleFrameRange;
        else if (moving && currentFrameRange != walkFrameRange)
                currentFrameRange = walkFrameRange;
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

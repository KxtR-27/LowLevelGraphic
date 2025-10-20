package player;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Tickable {
    private final Sprite sprite = new Sprite();

    // Will (potentially) change every tick when implemented
    @SuppressWarnings("FieldMayBeFinal")
    private Point location = new Point(100, 100);

    // Makes more sense to put in object scope imo
    @SuppressWarnings("FieldCanBeLocal")
    private final int scale = 3;


    public void onTick(double _deltaTime) {
        sprite.tick();
    }


    public void draw(Graphics2D g2) {
        // Store current transform
        AffineTransform t = g2.getTransform();

        // Scale before drawing player sprite
        g2.scale(scale, scale);

        // Draw sprite
        sprite.draw(g2, new Point(location.x / scale, location.y / scale));

        // Reset scale before anything else can be drawn elsewhere
        g2.setTransform(t);
    }
}

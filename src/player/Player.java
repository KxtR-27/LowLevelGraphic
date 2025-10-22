package player;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Tickable {
    private final Sprite sprite = new Sprite();

    private final Point location = new Point(100, 100);
    private final Point delta = new Point(0, 0);

    private final PlayerController controller = new PlayerController(this);

    public void connectControllerTo(Component parent) {
        parent.addKeyListener(controller);
        System.out.printf("Player controller connected!%n");
    }


    public void onTick(double _deltaTime) {
        // ^^ time change would be too small ^^

        // Move
        location.x += delta.x;
        location.y += delta.y;

        sprite.changeAnimationIfNeeded(delta);
        sprite.tick();
    }


    /** Draw player's sprite, but keep sprite scale from affecting the entire context */
    public void draw(Graphics2D g2) {
        AffineTransform t = g2.getTransform();

        int scale = 40;
        g2.scale(scale, scale);

        sprite.draw(g2, new Point(location.x / scale, location.y / scale));

        g2.setTransform(t);
    }


    void changeVelocity(Point change) {
        delta.x += change.x;
        delta.y += change.y;
    }
}

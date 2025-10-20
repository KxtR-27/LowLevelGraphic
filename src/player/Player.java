package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import static java.awt.event.KeyEvent.*;

public class Player extends Tickable implements KeyEventDispatcher {
    private final Sprite sprite = new Sprite();

    private final Point location = new Point(100, 100);
    private final Point delta = new Point(0, 0);

    // Makes more sense to put in object scope imo
    @SuppressWarnings("FieldCanBeLocal")
    private final int scale = 8;

    public Player() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public void onTick(double _deltaTime) {
        // ^^ time change would be too small ^^

        // Move
        int speed = 10;
        location.x += delta.x * speed;
        location.y += delta.y * speed;

        delta.x = 0;
        delta.y = 0;

        sprite.tick();
    }

    /** Draw player's sprite, but keep sprite scale from affecting the entire context */
    public void draw(Graphics2D g2) {
        AffineTransform t = g2.getTransform();

        g2.scale(scale, scale);

        sprite.draw(g2, new Point(location.x / scale, location.y / scale));

        g2.setTransform(t);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> delta.y = -1;
            case VK_A -> delta.x = -1;
            case VK_S -> delta.y = 1;
            case VK_D -> delta.x = 1;

            default -> {return false;}
        }

        return true;
    }
}

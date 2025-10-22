package player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class PlayerController extends KeyAdapter {
    private final Set<Integer> debounceSet = new HashSet<>();
    private final Player controlledPlayer;

    public PlayerController(Player controlledPlayer) {
        this.controlledPlayer = controlledPlayer;
    }


    private void changePlayerDelta(KeyEvent e, boolean pressed) {
        int keyCode = e.getKeyCode();
        Point delta = new Point(0, 0);

        System.out.printf("%nKey input passed [%s] [%b]%n", KeyEvent.getKeyText(keyCode), pressed);

        switch (keyCode) {
            case VK_W -> delta.y = -1;
            case VK_A -> delta.x = -1;
            case VK_S -> delta.y = 1;
            case VK_D -> delta.x = 1;
        }

        if (!pressed) {
            delta.x *= -1;
            delta.y *= -1;
        }

        System.out.printf("Resulting vector-point [%s]", delta);
        controlledPlayer.changeVelocity(delta);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (!debounced(e)) {
            debounce(e);
            changePlayerDelta(e, true);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (debounced(e)) {
            unbounce(e);
            changePlayerDelta(e, false);
        }
    }


    private boolean debounced(KeyEvent e) {
        return debounceSet.contains(e.getKeyCode());
    }
    private void debounce(KeyEvent e) {
        debounceSet.add(e.getKeyCode());
    }
    private void unbounce(KeyEvent e) {
        debounceSet.remove(e.getKeyCode());
    }
}

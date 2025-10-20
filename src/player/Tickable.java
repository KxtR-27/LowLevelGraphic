package player;

import javax.swing.*;

public abstract class Tickable extends JComponent {
    private double secondsAtLastTick;

    public Tickable() {
        secondsAtLastTick = System.currentTimeMillis() / 1000.0;
    }

    public void tick() {
        double secondsNow = System.currentTimeMillis() / 1000.0;
        double deltaTime = secondsNow - secondsAtLastTick;
        onTick(deltaTime);
        secondsAtLastTick = secondsNow;
    }

    protected abstract void onTick(double deltaTime);
}

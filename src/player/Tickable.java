package player;

public abstract class Tickable {
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

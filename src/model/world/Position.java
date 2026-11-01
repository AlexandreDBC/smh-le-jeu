package model.world;

public class Position {
    private Zone zone;
    private int x;
    private int y;

    public Position(Zone zone, int x, int y) {
        this.zone = zone;
        this.x = x;
        this.y = y;
    }

    public Zone getZone() {
        return zone;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

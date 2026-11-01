package model.world;

import model.inventory.Key;

public class Building extends Zone implements Unlockable {
    private boolean isLocked;
    private Key key;

    public Building(String name, int width, int height, boolean isLocked) {
        super(name, width, height);
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        isLocked = false;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public boolean isBuilding() {
        return true;
    }

    @Override
    public String getLabel() {
        return super.getLabel() + (isLocked ? " (verrouillé)" : "");
    }
}

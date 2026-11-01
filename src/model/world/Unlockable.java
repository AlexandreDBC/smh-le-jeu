package model.world;

import model.inventory.Key;

public interface Unlockable {
    boolean isLocked = false;

    public default boolean isBuilding() { return false; };
    public default boolean isChest() { return false; };

    public boolean isLocked();

    public Key getKey();

    public void setKey(Key key);

    public void unlock();

    public String getName();
}

package model.inventory;

import model.world.Unlockable;

public class Key extends Item {
    private Unlockable unlocks;

    public Key(String name, String description, Unlockable unlocks) {
        super(name, description);
        this.unlocks = unlocks;
        unlocks.setKey(this);
    }

    public Unlockable getUnlocks() {
        return unlocks;
    }

    public boolean isKey() {
        return true;
    }

    @Override
    public String getDescription() {
        return (super.getDescription() + " Cette clef ouvre : " + unlocks.getName());
    }

    @Override
    public String getStringUtilisation() {
        return "utiliser";
    }

}

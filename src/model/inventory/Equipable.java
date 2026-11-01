package model.inventory;

import model.entity.Player;

public abstract class Equipable extends Item {
    private boolean equipped;
    public abstract int getBonusAttack();
    public abstract int getBonusDefense();

    public Equipable(String name, String description) {
        super(name, description);
        this.equipped = false;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void equip() {
        this.equipped = true;
    }

    public void unequip() {
        this.equipped = false;
    }

    @Override
    public String getStringUtilisation() {
        return "equiper";
    }

    public abstract void equipTo(Player player);
    public abstract boolean verifyInventoryPlayer(Player player);
    public abstract Item removeItem(Player player);
}

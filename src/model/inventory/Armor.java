package model.inventory;

import model.entity.Player;

public class Armor extends Equipable {
    private int protection;

    public Armor(String name, String description, int protection) {
        super(name, description);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }

        @Override
    public String toString() {
        return super.toString() + " (Protection : " + protection + ")";
    }

    @Override
    public int getBonusAttack() {
        return 0; // L'arme donne son score de dégâts
    }

    @Override
    public int getBonusDefense() {
        return this.protection; // Une arme ne protège pas par défaut
    }

    @Override
    public void equipTo(Player player) {
        player.equipArmor(this); // "this" est obligatoirement une Armor !
    }

    @Override
    public boolean verifyInventoryPlayer(Player player) {
        return player.getEquippedArmor() != null;
    }

    @Override
    public Armor removeItem(Player player) {
        return player.removeArmor();
    }
}

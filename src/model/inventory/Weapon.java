package model.inventory;

import model.entity.Player;

public class Weapon extends Equipable {
    private int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

        @Override
    public String toString() {
        return super.toString() +  " (Dégâts : " + damage + ")";
    }

    @Override
    public int getBonusAttack() {
        return this.damage; // L'arme donne son score de dégâts
    }

    @Override
    public int getBonusDefense() {
        return 0; // Une arme ne protège pas par défaut
    }

    @Override
    public void equipTo(Player player) {
        player.equipWeapon(this); // "this" est obligatoirement une Weapon ici !
    }

    @Override
    public boolean verifyInventoryPlayer(Player player) {
        return player.getEquippedWeapon() != null;
    }

    public Weapon removeItem(Player player) {
        return player.removeWeapon();
    }
}

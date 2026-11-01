package model.entity;

import model.inventory.Armor;
import model.inventory.Inventory;
import model.inventory.PowerUp;
import model.inventory.Weapon;
import model.world.Position;

public class Player implements Combatant {
    private String name;
    private int health;
    private Position position;
    private Inventory inventory;

    // Les 3 slots d'équipement stricts
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private PowerUp equippedPowerUp;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.inventory = new Inventory();
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.equippedPowerUp = null;
    }

    // --- CALCUL DES STATISTIQUES ---

    @Override
    public int getAttack() {
        int totalAttack = 10; // Attaque de base

        if (equippedWeapon != null) totalAttack += equippedWeapon.getBonusAttack();
        if (equippedPowerUp != null) totalAttack += equippedPowerUp.getBonusAttack();

        return totalAttack;
    }

    @Override
    public int getDefense() {
        int totalDefense = 10; // Défense de base

        if (equippedArmor != null) totalDefense += equippedArmor.getBonusDefense();
        if (equippedPowerUp != null) totalDefense += equippedPowerUp.getBonusDefense();

        return totalDefense;
    }

    // --- GESTION DE L'ÉQUIPEMENT (L'ÉCHANGE) ---

    public void equipWeapon(Weapon newWeapon) {
        if (this.equippedWeapon != null) {
            this.inventory.addItem(this.equippedWeapon); // Remet l'ancienne dans l'inventaire
        }
        this.equippedWeapon = newWeapon;
    }

    public void equipArmor(Armor newArmor) {
        if (this.equippedArmor != null) {
            this.inventory.addItem(this.equippedArmor);
        }
        this.equippedArmor = newArmor;
    }

    public void equipPowerUp(PowerUp newPowerUp) {
        if (this.equippedPowerUp != null) {
            this.inventory.addItem(this.equippedPowerUp);
        }
        this.equippedPowerUp = newPowerUp;
    }
    public Weapon removeWeapon() {
        if (this.equippedWeapon != null) {
            Weapon tmp = this.equippedWeapon;
            this.inventory.removeItem(this.equippedWeapon);
            return tmp;
        }
        return null;
    }

    public Armor removeArmor() {
        if (this.equippedArmor != null) {
            Armor tmp = this.equippedArmor;
            this.inventory.removeItem(this.equippedArmor);
            return tmp;
        }
        return null;
    }

    public PowerUp removePowerUp() {
        if (this.equippedPowerUp != null) {
            PowerUp tmp = this.equippedPowerUp;
            this.inventory.removeItem(this.equippedPowerUp);
            return tmp;
        }
        return null;
    }
    // --- GETTERS & SETTERS CLASSIQUES ---

    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public Armor getEquippedArmor() { return equippedArmor; }
    public PowerUp getEquippedPowerUp() { return equippedPowerUp; }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void addHealth(int food) { this.health += food; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public Inventory getInventory() { return inventory; }
}
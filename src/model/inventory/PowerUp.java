package model.inventory;

import model.entity.Player;

public class PowerUp extends Equipable {
    private String effect;

    public PowerUp(String name, String description, String effect) {
        super(name, description);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return super.toString() +  " (Effet : " + effect + ")";
    }

    @Override
    public int getBonusAttack() {
        return 0; // L'arme donne son score de dégâts
    }

    @Override
    public int getBonusDefense() {
        return 0; // Une arme ne protège pas par défaut
    }

    @Override
    public void equipTo(Player player) {
        player.equipPowerUp(this); // "this" est obligatoirement un PowerUp !
    }

    @Override
    public boolean verifyInventoryPlayer(Player player) {
        return player.getEquippedPowerUp() != null;
    }

    @Override
    public PowerUp removeItem(Player player) {
        return player.removePowerUp();
    }
}

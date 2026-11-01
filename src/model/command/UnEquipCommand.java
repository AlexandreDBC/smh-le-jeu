package model.command;

import model.entity.Player;
import model.inventory.Equipable;

public class UnEquipCommand implements Command {
    private Player player;
    private Equipable item;

    public UnEquipCommand(Player player, Equipable item) {
        this.player = player;
        this.item = item;
    }

    @Override
    public String getLabel() {
        return "Enlever " + item.getName();
    }

    @Override
    public void execute() {
        // Logic to unequip the item
    }

}

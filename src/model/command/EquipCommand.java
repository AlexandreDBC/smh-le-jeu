package model.command;

import model.entity.Player;
import model.inventory.Equipable;

public class EquipCommand implements Command {
    private Player player;
    private Equipable item;

    public EquipCommand(Player player, Equipable item) {
        this.player = player;
        this.item = item;
    }

    @Override
    public String getLabel() {
        return "Equiper " + item.getName();
    }

    @Override
    public void execute() {
        // si null != null => faux
        // si obj != null => vrai
        if(item.verifyInventoryPlayer(player)) {
            item.removeItem(player);
            item.equipTo(player);
            player.getInventory().removeItem(item);
        }
        else {
            item.equipTo(player);
            player.getInventory().removeItem(item);
        }
    }
}

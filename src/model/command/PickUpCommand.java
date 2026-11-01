package model.command;

import model.core.GameEngine;
import model.entity.Player;
import model.inventory.Item;
import model.world.Cell;

public class PickUpCommand implements Command {
    private Player player;
    private Cell cell;

    public PickUpCommand(Player player, Cell cell) {
        this.player = player;

        this.cell = cell;
    }

    public Item getItem() {
        return cell.getItem();
    }

    @Override
    public String getLabel() {
        return "Ramasser " + getItem().getName();
    }

    @Override
    public void execute() {
        if (player.getInventory().getItems().size() >= player.getInventory().maxCapacity) {
            if(GameEngine.getInstance().getCurrentGame() != null) {
                GameEngine.getInstance().getCurrentGame()
                        .setMessage("Inventaire plein ! Impossible de ramasser " + getItem().getName());
            }
        } else {
            Item item = cell.getItem();
            cell.removeItem();
            player.getInventory().addItem(item);
        }
    }
}

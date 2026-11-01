package model.command;

import model.entity.Player;
import model.inventory.Item;
import model.world.Cell;

public class DropCommand implements Command {

    private Player player;
    private Item item;

    public DropCommand(Player player, Item item) {
        this.player = player;
        this.item = item;
    }

    @Override
    public String getLabel() {
        return "Jeter " + item.getName();
    }

    @Override
    public void execute() {
        Cell cell = player.getPosition().getZone().getCellAt(player.getPosition().getX(), player.getPosition().getY());
        if (!(cell.hasItem())) {
            player.getInventory().removeItem(item);
            cell.addItem(item);
        }
    }
}

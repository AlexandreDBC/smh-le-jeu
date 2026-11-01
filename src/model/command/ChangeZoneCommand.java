package model.command;

import model.entity.Player;
import model.inventory.Item;
import model.inventory.Key;
import model.world.Building;
import model.world.Position;
import model.world.Zone;

import java.util.Optional;

public class ChangeZoneCommand implements Command {
    private Player player;
    private Zone destination;

    public ChangeZoneCommand(Player player, Zone destination) {
        this.player = player;
        this.destination = destination;
    }

    @Override
    public String getLabel() {
        return "Aller dans " + destination.getLabel();
    }

    @Override
    public void execute() {
        if (destination.isBuilding()) {
            Building building = (Building) destination;
            if (building.isLocked()) {
                Optional<Item> key = player.getInventory().getItems().stream().filter(item -> item.equals(building.getKey())).findFirst();
                if (key.isPresent()) {
                    building.unlock();
                    player.getInventory().getItems().remove(key.get());
                }
                return;
            }
        }
        player.setPosition(new Position(destination, destination.getWidth() / 2, destination.getHeight() / 2));
    }
}

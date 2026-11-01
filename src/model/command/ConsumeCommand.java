package model.command;

import model.entity.Player;
import model.inventory.Food;

public class ConsumeCommand implements Command {
    private Player player;
    private Food food;

    public ConsumeCommand(Player player, Food food) {
        this.player = player;
        this.food = food;  
    }

    @Override
    public String getLabel() {
        return "Consommer " + food.getName();
    }

    @Override
    public void execute() {
        player.getInventory().removeItem(food);
        player.addHealth(food.getHeal());
    }
}

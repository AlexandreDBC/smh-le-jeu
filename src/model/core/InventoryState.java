package model.core;

import model.inventory.*;

import java.util.List;

public class InventoryState extends AbstractListState<Item> {
    private Inventory inventory;

    public InventoryState(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    protected List<Item> getItems() {
        return inventory.getItems();
    }

    @Override
    public void enter() {
        Game game = GameEngine.getInstance().getCurrentGame();
        if (game != null) {
            game.start();
        }
    }

    @Override
    public void update() {
        Game game = GameEngine.getInstance().getCurrentGame();
        if (game != null) {
            game.update();
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

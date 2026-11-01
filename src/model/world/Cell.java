package model.world;

import model.inventory.Item;

public class Cell {
    private boolean accessible;
    private Item item;

    public Cell(boolean accessible, Item item) {
        this.accessible = accessible;
        this.item = item;
    }

    public Cell(boolean accessible) {
        this(accessible, null);
    }

    public boolean isAccessible() {
        return accessible;
    }

    public Item getItem() {
        return item;
    }

    public Item removeItem() {
        Item removedItem = item;
        this.item = null;
        return removedItem;
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public boolean hasItem() {
        return item != null;
    }
}

package model.world;

import model.inventory.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Zone {
    private String name;
    private int width;
    private int height;

    private List<Zone> connectedZones;
    private Cell[][] grid;

    public Zone(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.connectedZones = new ArrayList<>();
        this.grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.grid[y][x] = new Cell(true, null);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return getName();
    }

    public List<Zone> getConnectedZones() {
        return connectedZones;
    }

    public void connectTo(Zone other) {
        if (!this.connectedZones.contains(other)) {
            this.connectedZones.add(other);
            other.connectedZones.add(this);
        }
    }

    public Cell getCellAt(int x, int y) {
        return grid[y][x];
    }

    public void removeItem(Item item) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].getItem() == item) {
                    grid[y][x].removeItem();
                    return;
                }
            }
        }
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].hasItem()) {
                    items.add(grid[y][x].getItem());
                }
            }
        }
        return items;
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(grid[y][x].hasItem()) {
                    cells.add(grid[y][x]);
                }
            }
        }
        return cells;
    }

    public boolean isBuilding() { return false; };

    public boolean isStreet() { return false; };

}

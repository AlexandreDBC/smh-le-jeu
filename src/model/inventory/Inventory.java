package model.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    final private List<Item> items;
    final public int maxCapacity;

    public Inventory() {
        this.items = new ArrayList<>();
        maxCapacity = 10;
        this.items.add(new Weapon("Cours de FLI","Les cours de FLI sont très casses têtes, " +
                "les envoyers sur un ennemi lui infligera de lourds dégâts"
                ,45));
        this.items.add(new Armor("Cours de FLI - ARMOR","Les cours de FLI étant très denses" +
                "ils offrent une protection très forte",45));
        this.items.add(new PowerUp("Cours de FLI - POWERUP","En équipant ce powerup l'utilisateur " +
                "devient très fort et très intelligent" +
                "ce qui lui permet d'avoir des effets venus d'un autre monde","Equipez seulement"));
    }

    public void addItem(Item item) {
        if (this.items.size() < maxCapacity) {
            this.items.add(item);
        }
    }

    public void removeItem(Item item) {
        // Logic to remove an item from the inventory
        this.items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void equipItem(Item item) {
        // Logic to equip an item
        // not in the increment we chose
    }
}

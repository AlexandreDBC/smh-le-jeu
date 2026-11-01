package model.inventory;

public abstract class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] " + getName() + " : " + getDescription();
    }

    // String renvoyer selon le type de l'objet
    // Exemple, si l'objet est un weapon alors ca sera équiper, si c'est un food c'est manger
    abstract public String getStringUtilisation();
}

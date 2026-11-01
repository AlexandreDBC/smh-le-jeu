package model.inventory;

public class Food extends Item {
    private int heal;

    public Food(String name, String description, int heal) {
        super(name, description);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }

        @Override
    public String toString() {
        return super.toString() +  " (Soigne : " + heal + " PV)";
    }

    @Override
    public String getStringUtilisation() {
        return "manger";
    }

}

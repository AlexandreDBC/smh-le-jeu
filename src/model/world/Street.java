package model.world;

public class Street extends Zone {

    public Street(String name, int width, int height) {
        super(name, width, height);
    }

    @Override
    public boolean isStreet() {
        return true;
    }
}

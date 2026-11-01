package model.world;

import java.util.ArrayList;
import java.util.List;

public class Campus {
    private List<Zone> zones;

    public Campus() {
        this.zones = new ArrayList<>();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    public List<Zone> getZones() {
        return zones;
    }
}

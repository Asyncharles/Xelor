package net.xelor.structures.energy.light;

import net.xelor.house.Room;
import net.xelor.structures.energy.Energy;

public class LightData extends Energy<Double, Room> {
    private final double aValue;
    private final Room bValue;
    private final boolean permanent;
    private final long duration;

    public LightData(double a, Room b, long duration) {
        this.aValue = a;
        this.bValue = b;
        this.permanent = false;
        this.duration = duration;
    }

    public LightData(double a, Room b, boolean permanent) {
        this.aValue = a;
        this.bValue = b;
        this.permanent = permanent;
        this.duration = 0L;
    }

    public LightData(double aValue, Room bValue, boolean permanent, long duration) {
        this.aValue = aValue;
        this.bValue = bValue;
        this.permanent = permanent;
        this.duration = duration;
    }

    @Override
    public Double getPrimaryValue() {
        return null;
    }

    @Override
    public Room getSecondaryValue() {
        return null;
    }

    @Override
    public boolean isPermanent() {
        return false;
    }

    @Override
    public long getDuration() {
        return 0;
    }

}

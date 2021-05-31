package net.xelor.structures.energy;

import net.xelor.structures.energy.types.EnergyType;

public class EnergyData {
    private final double a;
    private final EnergyType type;
    private final boolean permanent;
    private final long duration;

    public EnergyData(double a, EnergyType type, long duration) {
        this.a = a;
        this.type = type;
        this.permanent = false;
        this.duration = duration;
    }

    public EnergyData(double a, EnergyType type, boolean permanent) {
        this.a = a;
        this.type = type;
        this.permanent = permanent;
        this.duration = 0L;
    }

    public double getA() {
        return a;
    }

    public EnergyType getType() {
        return type;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public long getDuration() {
        return duration;
    }
}

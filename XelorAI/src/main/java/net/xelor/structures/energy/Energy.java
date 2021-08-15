package net.xelor.structures.energy;

import net.xelor.query.Searchable;

public abstract class Energy implements Searchable<Double, Double> {
    public abstract boolean isPermanent();
    public abstract long getDuration();
}

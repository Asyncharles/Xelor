package net.xelor.client.structures.energy;

import net.xelor.client.query.Searchable;

public abstract class Energy implements Searchable<Double, Double> {
    public abstract boolean isPermanent();
    public abstract long getDuration();
}

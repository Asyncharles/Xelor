package net.xelor.structures.energy;

import net.xelor.query.Searchable;

public abstract class Energy<T, V> implements Searchable {
    public abstract T getPrimaryValue();
    public abstract V getSecondaryValue();
    public abstract boolean isPermanent();
    public abstract long getDuration();
}

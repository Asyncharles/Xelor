package net.xelor.structures.targets;

import net.xelor.query.Searchable;

import java.util.ArrayList;
import java.util.List;

public final class DefaultTargetStrategy {
    private List<Searchable> searchableList = new ArrayList<>();

    public DefaultTargetStrategy() {
    }

    public DefaultTargetStrategy replaceSpecific(List<Searchable> searchables) {
        this.searchableList = searchables;
        return this;
    }

    public DefaultTargetStrategy averageWithPreviousTarget(TargetValue targetValue) {
        return this;
    }

    public DefaultTargetStrategy averageWithPreviousSearchable(Searchable searchable) {
        return this;
    }
}

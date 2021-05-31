package net.xelor.structures;

import java.util.List;
import java.util.Map;

public abstract class DataContainer<T> {
    protected abstract Map<Long, T> getDataMap();
    public abstract <K> List<T> retrieveData(String fieldName, K fieldValue, long... a) throws IllegalAccessException;
}

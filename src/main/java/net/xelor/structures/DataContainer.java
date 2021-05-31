package net.xelor.structures;

import net.xelor.query.SearchCursor;
import net.xelor.query.Searchable;

import java.util.List;
import java.util.Map;

public interface DataContainer<T extends Searchable> {
    public Map<Long, T> getDataMap();
    public <K> List<T> retrieveData(SearchCursor<T, K> search, long... a) throws IllegalAccessException;
    default <K> List<T> retrieveData(long... a) throws IllegalAccessException {
        return retrieveData(null, a);
    }
}

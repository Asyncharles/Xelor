package net.xelor.client.structures;

import net.xelor.client.engine.Engine;
import net.xelor.client.structures.targets.TargetValue;
import net.xelor.client.weather.WeatherData;
import net.xelor.client.query.SearchFilter;
import net.xelor.client.query.Searchable;
import net.xelor.client.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DataContainer<T extends Searchable> {
    /**
     * <p>
     *     The data map stores a list of generic objects that extends searchable and a {@link DataComponent}
     *     The data is assigned to a key timestamp, which represents the time of when the data was collected
     *     The data is also assigned to {@link WeatherData}
     *     Each timestamp can return a list of different data
     * </p>
     * @return {@link Map}
     */
    public Map<Long, Pair<DataComponent, List<T>>> getDataMap();

    /**
     * Adds data to the {@link #getDataMap()}
     * @param timestamp the key {@link Long}
     * @param t the value {@link Searchable}
     */
    default void addData(long timestamp, T t) {
        getDataMap().putIfAbsent(timestamp, Pair.of(DataComponent.getDataComponent(), new ArrayList<>()));
        getDataMap().get(timestamp).getRight().add(t);
    }

    /**
     * Adds data to the {@link #getDataMap()} assigned to a timestamp of when this function is called
     * @param t the value {@link Searchable}
     */
    default void addData(T t) {
        addData(System.currentTimeMillis(), t);
    }

    /**
     * Adds data to the {@link #getDataMap()}
     * @param timestamp the key {@link Long}
     * @param t the value {@link Searchable}
     * @param componentTimestamp the {@link DataComponent} timestamp
     */
    default void addDataWithTimestamp(long timestamp, T t, long componentTimestamp) {
        getDataMap().putIfAbsent(timestamp, Pair.of(DataComponent.getDataComponent(componentTimestamp), new ArrayList<>()));
        getDataMap().get(timestamp).getRight().add(t);
    }

    /**
     * <p>
     *     Function used to retrieve data from the {@link #getDataMap()}
     *     Used mostly by the {@link Engine} to calculate results depending from {@link TargetValue}
     *     This function can also be used by users and/or extern developer to search specific data
     * </p>
     * @param search {@link SearchFilter}
     * @param a the {@link #getDataMap()} keys - maximum length of 2
     * @param <V> the {@link SearchFilter} type
     * @return the data
     * @throws IllegalAccessException
     */
    public <V> List<T> retrieveData(SearchFilter<V> search, long... a) throws IllegalAccessException;

    /**
     * Calls the {@link #retrieveData(SearchFilter, long...)} function with a null cursor
     * @param a the {@link #getDataMap()} keys - maximum length of 2
     * @return the data
     * @throws IllegalAccessException
     */
    default List<T> retrieveData(long... a) throws IllegalAccessException {
        return retrieveData(null, a);
    }
}

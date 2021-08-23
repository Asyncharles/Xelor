package net.xelor.client.query;

import net.xelor.client.structures.Domain;
import net.xelor.client.structures.targets.TargetValue;
import net.xelor.client.house.RoomModel;

import java.util.Map;

public interface Searchable<T extends Number, V extends Number> {
    /**
     * The name of the {@link Searchable}
     * @return the name
     */
    public String getName();

    /**
     * The primary value is an object that extends number
     * This value is the primary value to store, that will be used by Xelor to determine a result depending on the {@link TargetValue}
     * @return {@link Number}
     */
    public T getPrimaryValue();

    /**
     * The secondary value is an object that extends number
     * This value is the secondary value to store, that will be used by Xelor correct and analyse the result calculated with the primary value and the {@link TargetValue}
     * @return {@link Number}
     */
    public V getSecondaryValue();

    /**
     * The {@link RoomModel} the {@link Searchable} was collected from
     * @return {@link RoomModel}
     */
    public RoomModel getRoom();

    /**
     * The {@link Searchable} {@link Domain}
     * @return {@link Domain}
     */
    public Domain getDomain();

    /**
     * All the fields name assigned to their value used when values are queried with a {@link SearchFilter}
     * @return {@link Map}
     */
    public Map<String, Object> getFields();
}

package net.xelor.structures.energy.light;

import net.xelor.house.RoomModel;
import net.xelor.structures.Domain;
import net.xelor.structures.energy.Energy;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LightData extends Energy {
    private final double aValue;
    private final double bValue;
    private final RoomModel room;
    private final boolean permanent;
    private final long duration;
    private final Map<String, Object> fieldsMap;

    public LightData(double a, double b, RoomModel room, long duration) {
        this(a, b, room, false, (duration == 0L ? 1L : duration));
    }

    public LightData(double a, double b, RoomModel room, boolean permanent) {
        this(a, b, room, permanent, 0L);
    }

    public LightData(double a, double b, RoomModel room, boolean permanent, long duration) {
        this.aValue = a;
        this.bValue = b;
        this.room = room;
        this.permanent = permanent;
        this.duration = (duration == 0L && !permanent ? 1L : duration);
        this.fieldsMap = setupFieldsMap();
    }

    @Override
    public String getName() {
        return "LightData";
    }

    @Override
    public Double getPrimaryValue() {
        return aValue;
    }

    @Override
    public Double getSecondaryValue() {
        return bValue;
    }

    @Override
    public RoomModel getRoom() {
        return room;
    }

    @Override
    public boolean isPermanent() {
        return permanent;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public Domain getDomain() {
        return Domain.ELECTRIC;
    }

    @Override
    public Map<String, Object> getFields() {
        return fieldsMap;
    }

    private Map<String, Object> setupFieldsMap() {
        final Map<String, Object> map = new HashMap<>();
        for (Field field : getClass().getDeclaredFields()) {
            try {
                map.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
            stringBuilder.append(" key=").append(entry.getKey()).append(" value=").append(entry.getValue());
        }
        return "LightData{" +
                "fieldsMap=" + stringBuilder.toString() +
                '}';
    }
}

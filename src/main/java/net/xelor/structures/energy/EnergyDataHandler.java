package net.xelor.structures.energy;

import net.xelor.structures.DataContainer;
import net.xelor.structures.energy.types.EnergyType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class EnergyDataHandler extends DataContainer<EnergyData> {
    private final Map<Long, EnergyData> dataMap;

    public EnergyDataHandler() {
        dataMap = new HashMap<>();
    }

    public boolean addPermanentData(long timestamp, double amount, EnergyType type) {
        try {
            dataMap.put(timestamp, new EnergyData(amount, type, true));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addData(long timestamp, double amount, EnergyType type, long duration) {
        try {
            dataMap.put(timestamp, new EnergyData(amount, type, duration));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Map<Long, EnergyData> getDataMap() {
        return dataMap;
    }

    @Override
    public <K> List<EnergyData> retrieveData(String fieldName, K fieldValue, long... a) throws IllegalAccessException {
        final boolean isNull = fieldValue == null && fieldName == null;
        final List<EnergyData> energyStructures = new ArrayList<>();
        if (a.length == 1) {
            if (isNull) {
                return Collections.singletonList(getDataMap().get(a[0]));
            }
            for (Map.Entry<Long, EnergyData> entry : dataMap.entrySet()) {
                if (entry.getKey().compareTo(a[0]) == 0) {
                    for (Field field : entry.getValue().getClass().getFields()) {
                        if (field.getName().equalsIgnoreCase(fieldName)) {
                            if (field.get(fieldValue.getClass()) == fieldValue) {
                                energyStructures.add(entry.getValue());
                            }
                        }
                    }
                }
            }
        } else if (a.length == 2) {
            final List<EnergyData> e = dataMap.entrySet().stream().filter(v -> v.getKey() > a[0] && v.getKey() < a[1]).map(Map.Entry::getValue).collect(Collectors.toList());
            if (isNull) {
                return e;
            } else {
                for (EnergyData structure : e) {
                    for (Field field : structure.getClass().getFields()) {
                        if (field.getName().equalsIgnoreCase(fieldName)) {
                            if (field.get(fieldValue.getClass()) == fieldValue) {
                                energyStructures.add(structure);
                            }
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("The timestamp parameter is incorrect");
        }
        return energyStructures;
    }
}

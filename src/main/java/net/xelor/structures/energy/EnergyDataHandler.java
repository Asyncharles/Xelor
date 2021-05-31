package net.xelor.structures.energy;

import net.xelor.structures.DataContainer;
import net.xelor.structures.energy.types.EnergyType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class EnergyDataHandler extends DataContainer<EnergyStructure> {
    private final Map<Long, EnergyStructure> dataMap;

    public EnergyDataHandler() {
        dataMap = new HashMap<>();
    }

    public boolean addPermanentData(long timestamp, double amount, EnergyType type) {
        try {
            dataMap.put(timestamp, new EnergyStructure(amount, type, true));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addData(long timestamp, double amount, EnergyType type, long duration) {
        try {
            dataMap.put(timestamp, new EnergyStructure(amount, type, duration));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Map<Long, EnergyStructure> getDataMap() {
        return dataMap;
    }

    @Override
    public <K> List<EnergyStructure> retrieveData(K param, long... a) throws IllegalAccessException {
        final boolean isNull = param == null;
        final List<EnergyStructure> energyStructures = new ArrayList<>();
        if (a.length == 1) {
            if (isNull) {
                return Collections.singletonList(getDataMap().get(a[0]));
            }
            for (Map.Entry<Long, EnergyStructure> entry : dataMap.entrySet()) {
                if (entry.getKey().compareTo(a[0]) == 0) {
                    Class<? extends Object> clazz = param.getClass();
                    for (Field field : entry.getValue().getClass().getFields()) {
                        if (field.getType() == clazz) {
                            if (field.get(clazz) == param) {
                                energyStructures.add(entry.getValue());
                            }
                        }
                    }
                }
            }
        } else if (a.length == 2) {
            if (isNull) {
                energyStructures.addAll(dataMap.entrySet().stream().filter(v -> v.getKey() > a[0] && v.getKey() < a[1]).map(Map.Entry::getValue).collect(Collectors.toList()));
            } else {
                List<EnergyStructure> e = dataMap.entrySet().stream().filter(v -> v.getKey() > a[0] && v.getKey() < a[1]).map(Map.Entry::getValue).collect(Collectors.toList());
                for (EnergyStructure structure : e) {
                    Class<? extends Object> clazz = param.getClass();
                    for (Field field : structure.getClass().getFields()) {
                        if (field.getType() == clazz) {
                            if (field.get(clazz) == param) {
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

package net.xelor.structures.energy.light;

import net.xelor.query.SearchCursor;
import net.xelor.structures.DataContainer;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class LightDataHandler implements DataContainer<LightData> {
    private final Map<Long, LightData> dataMap;

    public LightDataHandler() {
        dataMap = new HashMap<>();
    }

    @Override
    public Map<Long, LightData> getDataMap() {
        return null;
    }

    @Override
    public <K> List<LightData> retrieveData(SearchCursor<LightData, K> search, long... a) throws IllegalAccessException {
        final boolean isNull = search == null;
        final List<LightData> lightData = new ArrayList<>();
        if (a.length == 1) {
            if (isNull) {
                return Collections.singletonList(getDataMap().get(a[0]));
            }
            for (Map.Entry<Long, LightData> entry : dataMap.entrySet()) {
                if (entry.getKey().compareTo(a[0]) == 0) {
                    for (Field field : search.searchable().getFields()) {
                        if (field.getName().equalsIgnoreCase(search.fieldName())) {
                            if (field.get(search.searchable()) == search.fieldValue()) {
                                lightData.add(entry.getValue());
                            }
                        }
                    }
                }
            }
        } else if (a.length == 2) {
            final List<LightData> e = dataMap.entrySet().stream().filter(v -> v.getKey() > a[0] && v.getKey() < a[1]).map(Map.Entry::getValue).collect(Collectors.toList());
            if (isNull) {
                return e;
            } else {
                for (LightData data : e) {
                    for (Field field : search.searchable().getFields()) {
                        if (field.getName().equalsIgnoreCase(search.fieldName())) {
                            if (field.get(search.searchable()) == search.fieldValue()) {
                                lightData.add(data);
                            }
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("The timestamp parameter is incorrect");
        }
        return lightData;
    }

}

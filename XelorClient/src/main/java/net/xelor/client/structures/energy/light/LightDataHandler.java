package net.xelor.client.structures.energy.light;

import net.xelor.client.query.SearchFilter;
import net.xelor.client.structures.DataComponent;
import net.xelor.client.structures.DataContainer;
import net.xelor.client.utils.Pair;

import java.util.*;

public class LightDataHandler implements DataContainer<LightData> {
    private final Map<Long, Pair<DataComponent, List<LightData>>> dataMap;

    public LightDataHandler() {
        dataMap = new HashMap<>();
    }

    @Override
    public Map<Long, Pair<DataComponent, List<LightData>>> getDataMap() {
        return dataMap;
    }

    @Override
    public <V> List<LightData> retrieveData(SearchFilter<V> search, long... a) {
        final boolean isNull = search == null;
        final List<LightData> lightData = new ArrayList<>();
        if (a.length == 1) {
            if (isNull) {
                return getDataMap().get(a[0]).getRight();
            }
            for (Map.Entry<Long, Pair<DataComponent, List<LightData>>> entry : dataMap.entrySet()) {
                if (entry.getKey().compareTo(a[0]) == 0) {
                    for (LightData data : entry.getValue().getRight()) {
                        for (Map.Entry<String, Object> aEntry : data.getFields().entrySet()) {
                            if (aEntry.getKey().equalsIgnoreCase(search.fieldName()) && aEntry.getValue().equals(search.fieldValue())) {
                                lightData.add(data);
                            }
                        }
                    }
                }
            }
        } else if (a.length % 2 == 0 && a.length != 0) {
            final List<LightData> e = new ArrayList<>();
            for (int index = 0; index < a.length; index += 2) {
                e.addAll(extractValuesWithDuration(dataMap.entrySet(), a[index], a[index + 1]));
            }
            if (isNull) {
                return e;
            } else {
                for (LightData data : e) {
                    for (Map.Entry<String, Object> aEntry : data.getFields().entrySet()) {
                        if (aEntry.getKey().equalsIgnoreCase(search.fieldName()) && aEntry.getValue().equals(search.fieldValue())) {
                            lightData.add(data);
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("The timestamp parameter is incorrect");
        }
        return lightData;
    }

    private List<LightData> extractValuesWithDuration(Set<Map.Entry<Long, Pair<DataComponent, List<LightData>>>> entry, Long a, Long b) {
        final List<LightData> e = new ArrayList<>();
        for (Map.Entry<Long, Pair<DataComponent, List<LightData>>> v : entry) {
            if (v.getKey() >= a && v.getKey() <= b) {
                e.addAll(v.getValue().getRight());
            }
        }
        return e;
    }
}

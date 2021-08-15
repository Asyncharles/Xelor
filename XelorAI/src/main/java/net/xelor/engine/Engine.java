package net.xelor.engine;

import net.xelor.structures.DataContainer;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Engine implements Runnable {
    public static List<DataContainer<?>> getDataContainers() {
        try {
            final List<DataContainer<?>> dataContainers = new ArrayList<>();
            final Reflections reflections = new Reflections("net.xelor.structures");
            final Set<Class<? extends DataContainer>> containers = reflections.getSubTypesOf(DataContainer.class);
            for (Class<? extends DataContainer> clazz : containers) {
                dataContainers.add(clazz.newInstance());
            }
            return dataContainers;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

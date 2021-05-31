package net.xelor.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataService {
    private static final Map<Class<? extends DataService>, DataService> dataServiceMap = new HashMap<>();

    private final String name;

    public DataService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void init();

    public abstract void shutdown();

    public static void registerServices() {
        Class<? extends DataService>[] classes = new Class[]{MongoService.class};

        for (Class<? extends DataService> clazz : classes) {
            try {
                dataServiceMap.putIfAbsent(clazz, clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<DataService> getServices() {
        return new ArrayList<>(dataServiceMap.values());
    }
}
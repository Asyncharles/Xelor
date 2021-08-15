package net.xelor.house;

import net.xelor.devices.Device;
import net.xelor.structures.targets.TargetManifest;
import net.xelor.structures.targets.TargetValue;

import java.util.*;

public class RoomModel extends TargetManifest {
    private final int id;
    private final RoomType roomType;
    private final boolean isPrimary;
    private final double squareMeter;
    private final List<Device> devices;

    private boolean hasWindows;
    private int averageTimeSpent;
    private boolean activeHandling;
    private Map<Short, TargetValue> targetValueMap;

    public RoomModel(int id, RoomType roomType, boolean isPrimary, double squareMeter) {
        this.id = id;
        this.roomType = roomType;
        this.isPrimary = isPrimary;
        this.squareMeter = squareMeter;
        this.devices = new ArrayList<>();
        this.targetValueMap = new LinkedHashMap<>();
    }

    public int getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public double getSquareMeter() {
        return squareMeter;
    }

    public List<Device> getEntryDevices() {
        return devices;
    }

    public boolean isHasWindows() {
        return hasWindows;
    }

    public int getAverageTimeSpent() {
        return averageTimeSpent;
    }

    public boolean isActiveHandling() {
        return activeHandling;
    }

    public void setTargetValueMap(Map<Short, TargetValue> targetValueMap) {
        this.targetValueMap = targetValueMap;
    }

    @Override
    public Map<Short, TargetValue> getTimeTargets() {
        return new LinkedHashMap<>(targetValueMap);
    }
}

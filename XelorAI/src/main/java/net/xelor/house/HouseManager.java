package net.xelor.house;

import net.xelor.devices.Device;

import java.util.*;

public final class HouseManager {
    private final Map<RoomType, List<RoomModel>> roomConfiguratorMap;
    private final String houseName;

    public HouseManager(final String houseName) {
        this.houseName = houseName;
        this.roomConfiguratorMap = new HashMap<>();
        initializeRoomMap();
    }

    public void configRoom(RConfig config) {
        if (getRoomById(config.id()).isEmpty()) {
            roomConfiguratorMap.get(config.getRoomType()).add(new RoomModel(config.id(), config.getRoomType(), config.isPrimary(), config.getSquareMeters()));
        }
        throw new IllegalArgumentException("A room already owns this ID!");
    }

    public Optional<RoomModel> getRoomById(int id) {
        for (List<RoomModel> roomModels : roomConfiguratorMap.values()) {
            Optional<RoomModel> optional = roomModels.stream().filter(r -> r.getId() == id).findFirst();
            if (optional.isPresent()) return optional;
        }
        return Optional.empty();
    }

    public void addDeviceToRoom(int id, Device device) {
        roomConfiguratorMap.values().stream().filter(r -> {
            r.stream().filter(rm -> rm.getId() == id).findFirst().ifPresent(v -> v.getEntryDevices().add(device));
            return true;
        });
    }

    public String getHouseName() {
        return houseName;
    }

    private void initializeRoomMap() {
        for (RoomType roomType : RoomType.values()) {
            roomConfiguratorMap.put(roomType, new ArrayList<>());
        }
    }
}

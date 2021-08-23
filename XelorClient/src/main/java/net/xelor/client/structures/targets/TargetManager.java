package net.xelor.client.structures.targets;

import net.xelor.client.XelorModule;
import net.xelor.client.house.RoomModel;

import java.util.Optional;

public class TargetManager {
    public static void setTarget(int roomId, TargetValue targetValue) {
        Optional<RoomModel> optional = XelorModule.getInstance().getHouseManager().getRoomById(roomId);

    }

    public static void setTarget(int roomId, DefaultTargetStrategy strategy) {
        Optional<RoomModel> optional = XelorModule.getInstance().getHouseManager().getRoomById(roomId);

    }

    public static void setTarget(int roomId, TargetValue targetValue, DefaultTargetStrategy strategy) {
        Optional<RoomModel> optional = XelorModule.getInstance().getHouseManager().getRoomById(roomId);

    }
}

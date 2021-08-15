package net.xelor.structures.targets;

import net.xelor.XelorModule;
import net.xelor.house.RoomModel;

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

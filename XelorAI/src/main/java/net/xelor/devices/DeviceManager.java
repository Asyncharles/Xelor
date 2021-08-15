package net.xelor.devices;

import java.util.HashSet;
import java.util.Set;

public class DeviceManager {
    private final Set<Device> deviceSet;

    public DeviceManager() {
        deviceSet = new HashSet<>();
    }

    public void registerNewDevice(DeviceAdapter adapter) {
        deviceSet.add(adapter.adaptExternDevice());
    }

    public void connectAllDeviceInstances() {
        deviceSet.forEach(device -> {
            device.toggleDevice(device.connect());
        });
    }
}

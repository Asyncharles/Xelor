package net.xelor.client.devices.information;

import net.xelor.client.devices.Device;

public abstract class OutputDevice implements Device {
    public abstract void publish(byte[] info);
    public abstract String getChannel();
    public abstract <R> byte[] translateInformation(R r);
}

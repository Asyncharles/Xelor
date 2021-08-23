package net.xelor.client.devices.information;

import net.xelor.client.devices.Device;

import java.util.List;

public abstract class InputDevice implements Device {
    public abstract void read(byte[] readings);
    public abstract <R> ITranslator<R> getTranslator();
    public abstract <R> List<R> getBuffer();
}

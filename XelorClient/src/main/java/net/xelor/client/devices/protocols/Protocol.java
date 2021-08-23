package net.xelor.client.devices.protocols;

public abstract class Protocol {
    private final String name;
    private final StreamProtocols protocol;

    public Protocol(String name, StreamProtocols protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public abstract void setup();
    public abstract boolean check();
    public abstract boolean send(String type, byte[] b);

    public String getName() {
        return name;
    }

    public StreamProtocols getProtocol() {
        return protocol;
    }
}

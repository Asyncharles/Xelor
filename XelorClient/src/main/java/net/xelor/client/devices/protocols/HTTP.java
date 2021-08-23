package net.xelor.client.devices.protocols;

public class HTTP extends Protocol {
    public HTTP() {
        super("HTTP", StreamProtocols.HTTP);
    }

    @Override
    public void setup() {

    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public boolean send(String type, byte[] b) {
        return false;
    }
}

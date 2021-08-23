package net.xelor.client.devices.information;

public interface ITranslator<R> {
    /**
     * Translate incoming information for extern devices
     * The incoming information is in an array of {@link Byte}
     * @param bytes the incoming information waiting to be translated
     * @return the translated byte array
     */
    public R translate(byte[] bytes);
}

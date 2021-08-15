package net.xelor.devices;

import net.xelor.devices.information.InputDevice;
import net.xelor.devices.information.OutputDevice;

public interface Device {
    /**
     * The {@link Device} id, used to identify the extern device
     * @return the id
     */
    public String getId();

    /**
     * The time when the {@link Device} issued the connection to Xelor
     * @return the connection timestamp
     */
    public long getConnectionTimestamp();

    /**
     * Connects the {@link Device} to Xelor
     * @return {@code true} if the {@link Device} has successfully connected, {@code false} otherwise
     */
    public boolean connect();

    /**
     * Shutdowns the {@link Device} connection
     */
    public void shutdown();

    /**
     *
     * @return {@code true} if the {@link Device} is connected, {@link false} otherwise
     */
    public boolean isActive();

    /**
     * Set if the {@link Device} is active
     * @param b if the {@link Device} is active or not
     */
    public void toggleDevice(boolean b);

    /**
     * <p>
     *     An {@link InputDevice} is a device that sends information to Xelor
     *     The InputDevice class stores the translated information sent by the extern device
     *     If this {@link Device} is not an {@link InputDevice} {@code null}
     * </p>
     * @return {@link InputDevice}
     */
    public InputDevice getInputDevice();

    /**
     * <p>
     *     An {@link OutputDevice} that receives information sent by Xelor
     *     This information is usually the result calculated by the {@link net.xelor.engine.Engine} to execute an action that was decided by the {@link net.xelor.structures.targets.TargetValue}
     *     The OutputDevice class stores the channel to publish the information
     *     If this {@link Device} is not an {@link OutputDevice} {@code null}
     * </p>
     * @return {@link OutputDevice}
     */
    public OutputDevice getOutputDevice();
}

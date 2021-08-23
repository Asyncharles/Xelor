package net.xelor.client.house;

public interface RConfig {
    /**
     * The {@link RoomModel} id, used to identify the room
     * @return the id
     */
    public int id();

    /**
     * The {@link RoomType} of the {@link RoomModel}
     * @return {@link RoomType}
     */
    public RoomType getRoomType();

    /**
     * <p>
     *     A primary room, is a room in which the users spend a consequent times
     *     In most cases, a primary room is the living room, or the kitchen
     * </p>
     * @return {@code true} if the {@link RoomModel} is a primary room, {@code false} otherwise
     */
    public boolean isPrimary();

    /**
     * The surface size of the {@link RoomModel}
     * @return the surface in square meters
     */
    public double getSquareMeters();
}

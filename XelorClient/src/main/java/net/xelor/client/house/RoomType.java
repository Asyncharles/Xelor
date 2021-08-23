package net.xelor.client.house;

public enum RoomType {
    HOUSE("House", false),
    LIVING("Salon", false),
    KITCHEN("Kitchen", false),
    BATHROOM("BathRoom", true),
    RESTROOM("Toilets", true),
    MASTER_BEDROOM("Master BedRoom", false),
    BEDROOM("BedRoom", true),
    CORRIDOR("Corridor", true);

    private final String name;
    private boolean defaultMultiple;

    RoomType(final String name, boolean defaultMultiple) {
        this.name = name;
        this.defaultMultiple = defaultMultiple;
    }

    public String getName() {
        return name;
    }

    public boolean hasMultiple() {
        return defaultMultiple;
    }

    public void setMultiple(boolean multiple) {
        this.defaultMultiple = multiple;
    }
}

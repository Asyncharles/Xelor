package net.xelor.house;

public enum Room {
    HOUSE("House", false),
    LIVING("Salon", false),
    KITCHEN("Kitchen", false),
    BATHROOM("BathRoom", true),
    RESTROOM("Toilets", true),
    MASTER_BEDROOM("Master BedRoom", false),
    BEDROOM("BedRoom", true),
    CORRIDOR("Corridor", true);

    private final String name;
    private final boolean multiple;

    Room(final String name, final boolean multiple) {
        this.name = name;
        this.multiple = multiple;
    }

    public String getName() {
        return name;
    }

    public boolean hasMultiple() {
        return multiple;
    }
}

package net.xelor.time;

import java.util.Arrays;
import java.util.Optional;

public enum DayTime {
    MIDNIGHT(23, 1),
    NIGHT(2, 5),
    EARLY_MORNING(6, 8),
    MORNING(9, 12),
    AFTERNOON(13, 18),
    EVENING(19, 22);

    private final int a, b;

    DayTime(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static Optional<DayTime> getDayTimeFromHour(int hour) {
        return Arrays.stream(values()).filter(v -> v.a >= hour && hour <= v.b).findFirst();
    }

    public static DayTime getCurrentDayTime() {
        //TODO : calculate the current day time
        return null;
    }
}

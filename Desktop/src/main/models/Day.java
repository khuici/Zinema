package main.models;

import lombok.Getter;

import static main.utils.Constants.*;

public class Day {
    @Getter private String name;
    @Getter private boolean open;

    public Day(String name, boolean open) {
        if (!WEEKDAY_MAP.containsKey(name)) {
            throw new IllegalArgumentException("Invalid day name: " + name);
        }

        this.name = name;
        this.open = open;
    }

    public static String getDayNameFromNumber(int dayNumber) {
        if (dayNumber < 1 || dayNumber > WEEKDAY_MAP.size()) {
            return null;
        }

        return WEEKDAY_MAP.keySet()
            .stream()
            .skip(dayNumber - 1)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid day number: " + dayNumber));
    }
}

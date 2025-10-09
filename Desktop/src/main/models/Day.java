package main.models;

import lombok.Getter;

import main.utils.Constants;

public class Day {
    @Getter private String name;
    @Getter private boolean open;

    public Day(String name, boolean open) {
        if (!Constants.WEEKDAY_MAP.containsKey(name)) {
            throw new IllegalArgumentException("Invalid day name: " + name);
        }

        this.name = name;
        this.open = open;
    }

    public static String getDayNameFromNumber(int dayNumber) {
        if (dayNumber < 1 || dayNumber > Constants.WEEKDAY_MAP.size()) {
            return null;
        }

        return Constants.WEEKDAY_MAP.keySet()
            .stream()
            .skip(dayNumber - 1)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid day number: " + dayNumber));
    }
}

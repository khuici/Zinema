package main.models.enums;

import lombok.Getter;

public enum Room {
    CHILDREN("Umeen Gela"),
    SUPER_HEROES("Super Heroien Gela"),
    THRILLER("Thriller Gela"),
    SCI_FI("Zientzia Fikzioko Gela"),
    COMEDY("Komedia Gela");

    @Getter public final String name;

    private Room(String name) {
        this.name = name;
    }
}

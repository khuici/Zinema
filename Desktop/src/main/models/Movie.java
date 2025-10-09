package main.models;

import lombok.Getter;
import lombok.Setter;
import main.models.enums.Room;

public class Movie {
    @Getter private String id;
    @Getter private String title;
    @Getter private Room room;

    @Getter @Setter private int tickets;

    public Movie(String id, String title, Room room) {
        this.id = id;
        this.title = title;
        this.room = room;

        this.tickets = 0;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s", id, title);
    }
}

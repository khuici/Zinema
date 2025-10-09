package main.models;

import lombok.Getter;

public class Show {
    @Getter private String id;
    @Getter private String time;
    @Getter private Movie movie;

    public Show(String id, String time, Movie movie) {
        this.id = id;
        this.time = time;
        this.movie = movie;
    }
}

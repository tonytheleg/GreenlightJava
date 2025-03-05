package org.waynetech.greenlight.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private LocalTime createdAt;
    private String title;
    private int year;
    private int runtime;
    private List<String> genres;
    private int version;

    public Movie(int id) {
        this.id = id;
        this.createdAt = LocalTime.now();
        this.title = "Casablanca";
        this.runtime = 102;
        this.genres = new ArrayList<String>();
        this.genres.add("drama");
        this.genres.add("romance");
        this.genres.add("war");
        this.version = 1;
    }
}

package org.waynetech.greenlight.dao;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class MoviesDao {
    public int id;
    public String createdAt;
    public String title;
    public int year;
    public int runtime;
    public List<String> genres;
    public int version;

    public MoviesDao(int id) {
        this.id = id;
        createdAt = LocalTime.now().toString();
        title = "Casablanca";
        runtime = 102;
        genres = Arrays.asList("drama", "romance", "war");
        version = 1;
    }
}

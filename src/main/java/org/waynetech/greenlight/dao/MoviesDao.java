package org.waynetech.greenlight.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MoviesDao {
    public int id;
    //public LocalTime createdAt;
    public String title;
    public int year;
    public int runtime;
    //public List<String> genres;
    public int version;

    public MoviesDao(int id) {
        this.id = id;
       // createdAt = LocalTime.now();
        title = "Casablanca";
        runtime = 102;
      //  genres = new ArrayList<String>();
        //genres.add("drama");
        //genres.add("romance");
        //genres.add("war");
        version = 1;
    }
}

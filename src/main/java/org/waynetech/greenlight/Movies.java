package org.waynetech.greenlight;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import org.waynetech.greenlight.dao.MoviesDao;
import com.google.gson.Gson;



public class Movies {
    void listMoviesHandler(HttpExchange exchange) throws IOException {
        String response = "List Movies Called\n";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    void showMovieHandler(int id, HttpExchange exchange) throws IOException {
        if (id != 0) {
            Gson gson = new Gson();
            MoviesDao movie = new MoviesDao(id);
            System.out.println(movie);
            // figure out how to convert class to json, or create write json function to do it
            String jsonResponse = gson.toJson(movie);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        } else {
            String response = "Error: invalid ID param\n";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    void createMovieHandler(int id, HttpExchange exchange) throws IOException {
        if (id != 0) {
            String response = "Post Movie Called with ID " + id + "\n";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "Error: invalid ID param\n";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

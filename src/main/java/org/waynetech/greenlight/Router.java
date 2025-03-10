package org.waynetech.greenlight;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class Router {
    // define handler for each endpoint
    static class HealthcheckHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{\"status\": \"available\"}\n";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class MovieHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            int id = readIDParam(exchange.getRequestURI());
            Movies movie = new Movies();

            // List movies
            if (exchange.getRequestURI().getPath().split("/").length == 3) {
                movie.listMoviesHandler(exchange);
            }

            switch(exchange.getRequestMethod()) {
                // Get movie
                case "GET":
                    movie.showMovieHandler(id, exchange);
                    break;
                // Create movie
                case "POST":
                    movie.createMovieHandler(id, exchange);
                    break;
                default:
                    System.out.println("ERROR: invalid request method\n");
            }
        }
    }

    private static int readIDParam(URI requestURI) {
        int id;
        String[] splitURI =  requestURI.getPath().split("/");
        try {
            id = Integer.parseInt(splitURI[splitURI.length - 1]);
        }
        catch (NumberFormatException e) {
            id = 0;
        }
        return id;
    }
}

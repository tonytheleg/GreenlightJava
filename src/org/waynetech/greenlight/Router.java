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
            // List movies
            if (exchange.getRequestURI().getPath().split("/").length == 3) {
                String response = "List Movies Called\n";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

            int id = readIDParam(exchange.getRequestURI());

            // Get movie
            if (exchange.getRequestMethod().equals("GET")) {
                if (id != 0) {
                    String response = "Get Movie Called with ID " + id + "\n";
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
            // Create movie
            } else if (exchange.getRequestMethod().equals("POST")) {
                if (id != 0) {
                    String response = "Get Movie Called with ID " + id + "\n";
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
            } else {
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

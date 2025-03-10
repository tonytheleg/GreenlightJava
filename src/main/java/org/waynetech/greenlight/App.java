package org.waynetech.greenlight;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.waynetech.greenlight.Router.*;


public class App {
    public static void main(String[] args) throws IOException {
        // Creates an HttpServer instance
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context for specific paths
        // this is basically how to define all the handler funcs
        server.createContext("/v1/healthcheck", new HealthcheckHandler());
        server.createContext("/v1/movies", new MovieHandler());

        // start the server
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port 8000");
    }
}

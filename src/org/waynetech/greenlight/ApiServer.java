package org.waynetech.greenlight;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

// DYNAMIC URL IDEA
// https://stackoverflow.com/questions/26815752/create-dynamically-contexts-for-com-sun-net-httpserver-httpserver-java

public class ApiServer {
    public static void main(String[] args) throws IOException {
        // Creates an HttpServer instance
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context for specific paths
        // this is basically how to define all the handler funcs
        server.createContext("/v1/healthcheck", new HealthcheckHandler());

        // start the server
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port 8000");
    }

    // define handler for each endpoint
    static class HealthcheckHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response =  "status: ready";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

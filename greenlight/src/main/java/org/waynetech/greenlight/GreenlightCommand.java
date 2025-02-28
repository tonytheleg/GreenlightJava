package org.waynetech.greenlight;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Command(name = "greenlight", mixinStandardHelpOptions = true, description = "Greenlight API Server")
public class GreenlightCommand {

    private int port = 8000;

    // define handler for each endpoint
    static class HealthcheckHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "status: ready";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    @Command
    void serve(@Option(names = {"-p", "--port"}, description = "port number for the server") int port) throws IOException {
        // Creates an HttpServer instance
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // create a context for specific paths
        // this is basically how to define all the handler funcs
        server.createContext("/v1/healthcheck", new HealthcheckHandler());

        // start the server
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port " + port);
    }

    public static void main(String[] args) {
        new CommandLine(new GreenlightCommand()).execute(args);
    }
}


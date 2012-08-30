package com.ad.tools;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class AdServer {

    private static final String DEFAULT_URI = "http://localhost:9998/";

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServerFactory.create(DEFAULT_URI);
        server.start();

        System.out.println("Server running");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}

package com.ad.api;

import com.ad.impl.ResponseBuilder;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.concurrent.Executor;

public class AdServer {

    private static final String ILLEGAL_ARGUMENT = "Use: 1) httpServerAddress (127.0.0.1:1111); 2) memCacheAddress (192.168.0.100:1111) 3) jdbc url (jdbc:postgresql://localhost/HerongDB?user=Herong&password=TopSecret)";

    public static void main(String[] args) throws IOException {
        if (args.length >= 3)
        {
            System.setProperty(Constants.MEM_CACHE_ADDRESS_KEY, args[1]);
            System.setProperty(Constants.JDBC_URL_KEY, args[2]);
            Integer daemonCount= Integer.parseInt(args[3]);

            HttpServer server = HttpServerFactory.create(Constants.HTTP_URI_PREFIX + args[0]);
            Executor executor = server.getExecutor();

            for (int i = 0; i < daemonCount; i++)
            {
                executor.execute(new ResponseBuilder.Daemon());
            }

            server.start();
            System.in.read();
            server.stop(0);
        }
        else
        {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
    }
}

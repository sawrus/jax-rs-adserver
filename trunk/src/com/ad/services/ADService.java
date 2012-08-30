package com.ad.services;

import com.ad.api.jdbc.JDBCProvider;
import com.ad.api.ws.ADRequest;
import com.ad.api.ws.ADResponse;

import javax.ws.rs.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Path("/ad")
public class ADService {
    private final JDBCProvider addb = JDBCProvider.POSTGRESQL_ADDB;

    @GET
    @Path("/json")
    @Produces("application/json")
    public ADResponse getJSONConfiguration(
            @DefaultValue("android") @QueryParam("platform") String platform,
            @QueryParam("app") String app,
            @QueryParam("pub_id") String pub_id,
            @QueryParam("version") String version,
            @QueryParam("width") Integer width,
            @QueryParam("height") Integer height
    ) {
        //todo: Analyze input data
        ADRequest adRequest = new ADRequest(platform, app, pub_id, version, width, height);

        System.out.println(adRequest);

        return ADResponse.fake().build();
    }

    @GET
    @Path("/html")
    @Produces("text/html")
    public String getHtmlBanner(
            @DefaultValue("android") @QueryParam("platform") String platform,
            @QueryParam("app") String app,
            @QueryParam("pub_id") String pub_id,
            @QueryParam("version") String version,
            @QueryParam("width") Integer width,
            @QueryParam("height") Integer height
    ) {
        //todo: Analyze input data
        ADRequest adRequest = new ADRequest(platform, app, pub_id, version, width, height);

        System.out.println(adRequest);

        return HTML_RESPONSE;
    }

    private static final String HTML_RESPONSE = "<html>\n" +
            "  <head>\n" +
            "    <title>Banner information</title>\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <h1>Banner</h1>\n" +
            "  </body>\n" +
            "</html>";
}

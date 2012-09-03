package com.ad.impl;

import com.ad.api.AdService;

import javax.ws.rs.*;

@Path("/ad")
public final class PackageService extends AdService {

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
        return processRequest(new ADRequest(platform, app, pub_id, version, width, height), ADResponse.class);
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
        return String.valueOf(processRequest(new ADRequest(platform, app, pub_id, version, width, height), Banner.class));
    }
}

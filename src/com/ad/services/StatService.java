package com.ad.services;

import com.ad.api.jdbc.JDBCProvider;
import com.ad.api.ws.StatRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/stat")
public class StatService {
    private final JDBCProvider addb = JDBCProvider.POSTGRESQL_ADDB;
    private final String C = "$C";
    private final String UPDATE_STAT_QUERY = "UPDATE campaign SET " + C + "  WHERE campaign_id=%s AND pub_id=%s";
    private final String UPDATE_IMPRESSIONS_QUERY = UPDATE_STAT_QUERY.replace(C, "impressions=impressions+1");
    private final String UPDATE_CLICKS_QUERY = UPDATE_STAT_QUERY.replace(C, "clicks=clicks+1");
    private final String UPDATE_COMPLETED_QUERY = UPDATE_STAT_QUERY.replace(C, "completed=completed+1");

    @GET
    @Path("/impression")
    @Produces("text/html")
    public Response registerImpression(@DefaultValue("android") @QueryParam("platform") String platform,
                                       @QueryParam("app") String app,
                                       @QueryParam("pub_id") Integer pub_id,
                                       @QueryParam("owner_app_id") Integer owner_app_id,
                                       @QueryParam("ads_offer_id") String ads_offer_id,
                                       @QueryParam("ads_banner_id") String ads_banner_id,
                                       @QueryParam("version") String version,
                                       @QueryParam("network_id") String network_id,
                                       @QueryParam("html_type") String html_type,
                                       @QueryParam("position") String position,
                                       @QueryParam("country") String country,
                                       @QueryParam("unlocker") Boolean unlocker
    ) {
        //todo: Analyze input data
        StatRequest statRequest = new StatRequest(platform, app, pub_id, owner_app_id, ads_offer_id, ads_banner_id, version, network_id, html_type, position, country, unlocker);

        addb.executeUpdate(String.format(UPDATE_IMPRESSIONS_QUERY, owner_app_id, ads_offer_id));
        return Response.ok().build();
    }

    @GET
    @Path("/click")
    @Produces("text/html")
    public Response registerClick(@DefaultValue("android") @QueryParam("platform") String platform,
                                  @QueryParam("app") String app,
                                  @QueryParam("pub_id") Integer pub_id,
                                  @QueryParam("owner_app_id") Integer owner_app_id,
                                  @QueryParam("ads_offer_id") String ads_offer_id,
                                  @QueryParam("ads_banner_id") String ads_banner_id,
                                  @QueryParam("version") String version,
                                  @QueryParam("network_id") String network_id,
                                  @QueryParam("html_type") String html_type,
                                  @QueryParam("position") String position,
                                  @QueryParam("country") String country,
                                  @QueryParam("unlocker") Boolean unlocker
    ) {
        //todo: Analyze input data
        StatRequest statRequest = new StatRequest(platform, app, pub_id, owner_app_id, ads_offer_id, ads_banner_id, version, network_id, html_type, position, country, unlocker);

        addb.executeUpdate(String.format(UPDATE_CLICKS_QUERY, owner_app_id, ads_offer_id));
        return Response.ok().build();
    }

    @GET
    @Path("/download")
    @Produces("text/html")
    public Response registerDownload(@DefaultValue("android") @QueryParam("platform") String platform,
                                     @QueryParam("app") String app,
                                     @QueryParam("pub_id") Integer pub_id,
                                     @QueryParam("owner_app_id") Integer owner_app_id,
                                     @QueryParam("ads_offer_id") String ads_offer_id,
                                     @QueryParam("ads_banner_id") String ads_banner_id,
                                     @QueryParam("version") String version,
                                     @QueryParam("network_id") String network_id,
                                     @QueryParam("html_type") String html_type,
                                     @QueryParam("position") String position,
                                     @QueryParam("country") String country,
                                     @QueryParam("unlocker") Boolean unlocker
    ) {
        //todo: Analyze input data
        StatRequest statRequest = new StatRequest(platform, app, pub_id, owner_app_id, ads_offer_id, ads_banner_id, version, network_id, html_type, position, country, unlocker);

        addb.executeUpdate(String.format(UPDATE_COMPLETED_QUERY, owner_app_id, ads_offer_id));
        return Response.ok().build();
    }
}

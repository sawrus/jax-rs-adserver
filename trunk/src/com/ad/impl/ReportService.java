package com.ad.impl;

import com.ad.api.AdService;

import javax.ws.rs.*;

@Path("/reporting")
public final class ReportService extends AdService {
    public ReportService() {
        setCacheMode(CacheMode.OFF);
        jdbcProvider.openConnection();
    }

    @GET
    @Path("/campaign")
    @Produces("application/json")
    public ReportResponse getReport(
            @DefaultValue("h2sdr2d") @QueryParam("pub_id") String pub_id,
            @DefaultValue("h2sdr2d") @QueryParam("campaign_id") String campaign_id
    ) {
        return processRequest(new ReportRequest(pub_id, campaign_id), ReportResponse.class);
    }
}

package com.ad.services;

import com.ad.api.jdbc.JDBCProvider;
import com.ad.api.ws.ReportRequest;
import com.ad.api.ws.ReportResponse;

import javax.ws.rs.*;

@Path("/reporting")
public class ReportService {
    private final JDBCProvider addb = JDBCProvider.POSTGRESQL_ADDB;

    @GET
    @Path("/campaign")
    @Produces("application/json")
    public ReportResponse getReport(
            @DefaultValue("h2sdr2d") @QueryParam("pub_id") String pub_id,
            @DefaultValue("h2sdr2d") @QueryParam("campaign_id") String campaign_id
    ) {
        try {
            ReportRequest reportRequest = new ReportRequest(pub_id, campaign_id);
            System.out.println(reportRequest);
            return ReportResponse.from(reportRequest).setProvider(addb).build();
        } catch (Exception e)
        {
            System.err.println(e);
            return ReportResponse.fake().build();
        }
    }
}

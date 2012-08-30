package com.ad.api.ws;

public class ReportRequest {
    public final String pubId;
    public final String campaignId;

    public ReportRequest(String pubId, String campaignId) {
        this.pubId = pubId;
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "pubId='" + pubId + '\'' +
                ", campaignId='" + campaignId + '\'' +
                '}';
    }
}

package com.ad.impl;

import com.ad.api.Cache;
import com.ad.api.Constants;

public final class ReportRequest implements Cache.CacheElement {
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

    @Override
    public String geKey() {
        return ReportRequest.class.getSimpleName() + Constants.CLASS_SEPARATOR + campaignId;
    }
}

package com.ad.impl;

import com.ad.api.Cache;
import com.ad.api.Constants;

public final class ADRequest implements Cache.CacheElement {
    public final String platformName;
    public final String applicationName;
    public final String pubId;
    public final String packagerVersion;
    public final Integer bannerWidth;
    public final Integer bannerHeight;

    public ADRequest(String platformName, String applicationName, String pubId, String packagerVersion, Integer bannerWidth, Integer bannerHeight) {
        this.platformName = platformName;
        this.applicationName = applicationName;
        this.pubId = pubId;
        this.packagerVersion = packagerVersion;
        this.bannerWidth = bannerWidth;
        this.bannerHeight = bannerHeight;
    }

    @Override
    public String toString() {
        return "ADRequest{" +
                "platformName='" + platformName + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", pubId='" + pubId + '\'' +
                ", packagerVersion='" + packagerVersion + '\'' +
                ", bannerWidth=" + bannerWidth +
                ", bannerHeight=" + bannerHeight +
                '}';
    }

    @Override
    public String geKey() {
        return ADRequest.class.getSimpleName() + Constants.CLASS_SEPARATOR + pubId;
    }
}

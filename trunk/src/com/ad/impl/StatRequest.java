package com.ad.impl;

public class StatRequest {
    public final String platformName;
    public final String applicationName;
    public final Integer pubId;
    public final Integer adsPubId;
    public final String adsOfferId;
    public final String adsBannerId;
    public final String packagerVersion;
    public final String networkId;
    public final String type;
    public final String position;
    public final String country;
    public final boolean unlocker;

    public StatRequest(String platformName, String applicationName, Integer pubId, Integer adsPubId, String adsOfferId, String adsBannerId, String packagerVersion, String networkId, String type, String position, String country, boolean unlocker) {
        this.platformName = platformName;
        this.applicationName = applicationName;
        this.pubId = pubId;
        this.adsPubId = adsPubId;
        this.adsOfferId = adsOfferId;
        this.adsBannerId = adsBannerId;
        this.packagerVersion = packagerVersion;
        this.networkId = networkId;
        this.type = type;
        this.position = position;
        this.country = country;
        this.unlocker = unlocker;
    }

    @Override
    public String toString() {
        return "StatRequest{" +
                "platformName='" + platformName + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", pubId=" + pubId +
                ", adsPubId=" + adsPubId +
                ", adsOfferId='" + adsOfferId + '\'' +
                ", adsBannerId='" + adsBannerId + '\'' +
                ", packagerVersion='" + packagerVersion + '\'' +
                ", networkId='" + networkId + '\'' +
                ", type='" + type + '\'' +
                ", position='" + position + '\'' +
                ", country='" + country + '\'' +
                ", unlocker=" + unlocker +
                '}';
    }
}

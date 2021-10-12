package com.akgarg.springcloudconfigclient.entity;

@SuppressWarnings("unused")
public class CloudResponse {

    private String nameConfig;
    private String locationConfig;
    private int statusCode;

    public CloudResponse(String nameConfig, String locationConfig, int statusCode) {
        this.nameConfig = nameConfig;
        this.locationConfig = locationConfig;
        this.statusCode = statusCode;
    }

    public CloudResponse() {
    }

    public String getNameConfig() {
        return nameConfig;
    }

    public void setNameConfig(String nameConfig) {
        this.nameConfig = nameConfig;
    }

    public String getLocationConfig() {
        return locationConfig;
    }

    public void setLocationConfig(String locationConfig) {
        this.locationConfig = locationConfig;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "CloudResponse{" +
                "nameConfig='" + nameConfig + '\'' +
                ", locationConfig='" + locationConfig + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}

package com.akgarg.microserviceconfiguration.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {

    @Value("${akgarg.app.name}")
    private String appName;

    @Value("${akgarg.app.description}")
    private String appDescription;

    public AppInfo() {
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appName='" + appName + '\'' +
                ", appDescription='" + appDescription + '\'' +
                '}';
    }
}

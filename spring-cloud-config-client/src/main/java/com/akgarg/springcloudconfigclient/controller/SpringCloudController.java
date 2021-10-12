package com.akgarg.springcloudconfigclient.controller;

import com.akgarg.springcloudconfigclient.entity.CloudResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("FieldCanBeLocal")
@RestController
@RefreshScope
public class SpringCloudController {

    // @Value("${com.name}")
    private final String nameConfig = "name";

    // @Value("${com.location}")
    private final String locationConfig = "location";


    @Value("${com.server.port}")
    private String port;


    @RequestMapping(value = "/get-props", method = RequestMethod.GET)
    public CloudResponse getCloudConfigs() {
        return new CloudResponse(nameConfig, locationConfig, 200);
    }

    @RequestMapping(value = "/get-configs", method = RequestMethod.GET)
    public String getConfig() {
        return port;
    }
}

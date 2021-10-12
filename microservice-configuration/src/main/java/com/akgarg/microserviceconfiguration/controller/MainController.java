package com.akgarg.microserviceconfiguration.controller;

import com.akgarg.microserviceconfiguration.model.AppInfo;
import com.akgarg.microserviceconfiguration.model.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${akgarg.prop.greeting}")
    private String greetMessage;

    @Autowired
    private AppInfo appInfo;

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public RequestResponse greet() {
        return new RequestResponse(greetMessage + " -> " + appInfo, 200);
    }
}

package com.akgarg.microserviceconfiguration;

import com.akgarg.microserviceconfiguration.model.AppInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConfigurationApplication.class, args);
    }

    @Bean
    public AppInfo getAppInfo() {
        return new AppInfo();
    }

}

package com.netcracker.metsko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ManagerApplication {
    public static void main(String arg[]) {
        SpringApplication.run(ManagerApplication.class, arg);
    }

}

package com.netcracker.metsko;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryApplication {
    public static void main(String arg[]) {
        SpringApplication.run(InventoryApplication.class, arg);

    }
}

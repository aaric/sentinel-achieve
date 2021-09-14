package com.example.stl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * client微服务应用
 *
 * @author Aaric, created on 2021-09-14T13:45.
 * @version 0.3.0-SNAPSHOT
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients
public class ClientApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}

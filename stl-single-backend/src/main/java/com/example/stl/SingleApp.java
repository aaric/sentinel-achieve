package com.example.stl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 测试单体应用
 *
 * @author Aaric, created on 2021-09-13T14:49.
 * @version 0.1.0-SNAPSHOT
 */
@SpringBootApplication
@EnableAsync
public class SingleApp {

    /**
     * main
     *
     * @param args custom inputs
     */
    public static void main(String[] args) {
        SpringApplication.run(SingleApp.class, args);
    }
}

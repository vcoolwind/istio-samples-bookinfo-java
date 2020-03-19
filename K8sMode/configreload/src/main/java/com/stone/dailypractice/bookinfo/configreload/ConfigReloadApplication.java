package com.stone.dailypractice.bookinfo.configreload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConfigReloadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigReloadApplication.class, args);
    }

}

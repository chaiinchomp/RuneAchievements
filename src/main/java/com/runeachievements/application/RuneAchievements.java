package com.runeachievements.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.runeachievements")
public class RuneAchievements {

    public static void main(String[] args) {
        SpringApplication.run(RuneAchievements.class, args);
    }

}

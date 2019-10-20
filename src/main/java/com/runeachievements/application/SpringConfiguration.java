package com.runeachievements.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeachievements.domain.AchievementsController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public AchievementsController getAchievementsController(ObjectMapper mapper) {
        return new AchievementsController(mapper);
    }

    @Bean
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

}

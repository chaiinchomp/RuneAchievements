package com.runeachievements.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeachievements.db.PostgresDbClient;
import com.runeachievements.domain.AchievementsController;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.dbcp.BasicDataSource;
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

    @Bean
    public PostgresDbClient getPostgresDbClient(BasicDataSource dataSource) {
        return new PostgresDbClient(dataSource);
    }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}

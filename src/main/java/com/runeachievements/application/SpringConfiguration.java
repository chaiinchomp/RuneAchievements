package com.runeachievements.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeachievements.db.PostgresDbClient;
import com.runeachievements.domain.AchievementsController;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.dbcp.BasicDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // allows use of @Transaction annotations
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
    public PostgresDbClient getPostgresDbClient(DSLContext dslContext) {
        return new PostgresDbClient(dslContext);
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

    @Bean
    public DataSourceTransactionManager getTransactionManager(BasicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringTransactionProvider getTransactionProvider(DataSourceTransactionManager transactionManager) {
        return new SpringTransactionProvider(transactionManager);
    }

    @Bean
    public TransactionAwareDataSourceProxy getTransactionAwareDataSource(BasicDataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public DataSourceConnectionProvider getConnectionProvider(
            TransactionAwareDataSourceProxy getTransactionAwareDataSource) {
        return new DataSourceConnectionProvider(getTransactionAwareDataSource);
    }

    @Bean
    public DefaultConfiguration getJooqConfig(
            DataSourceConnectionProvider connectionProvider, SpringTransactionProvider transactionProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.setConnectionProvider(connectionProvider);
        configuration.setTransactionProvider(transactionProvider);
        configuration.setSQLDialect(SQLDialect.POSTGRES);
        return configuration;
    }

    @Bean
    public DSLContext getDslContext(DefaultConfiguration jooqConfig) {
        return DSL.using(jooqConfig);
    }

}

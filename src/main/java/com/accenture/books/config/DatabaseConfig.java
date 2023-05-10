package com.accenture.books.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {

    @Bean
    @Qualifier("bookstoreDatasource")
    @ConfigurationProperties("app.datasource.bookstore")
    public HikariDataSource hikariBookStoreDatasource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Qualifier("bookstoreJdbcTemplate")
    public JdbcTemplate jdbcTemplateBookstore(
            @Qualifier("bookstoreDatasource") HikariDataSource hikariDataSource
    ) {
        return new JdbcTemplate(hikariDataSource);
    }
}

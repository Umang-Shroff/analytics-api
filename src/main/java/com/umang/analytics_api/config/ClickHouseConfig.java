package com.umang.analytics_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
public class ClickHouseConfig {

    @Bean
    public Connection clickHouseConnection() throws Exception {

        String url = "jdbc:clickhouse://localhost:8123/analytics";

        Properties props = new Properties();

        props.setProperty("user", "event_user");
        props.setProperty("password", "event123");

        return DriverManager.getConnection(url, props);
    }
}
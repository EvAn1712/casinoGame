package com.backcasino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class BackCasinoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackCasinoApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"); // Utilisation d'une base de données H2 en mémoire
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}


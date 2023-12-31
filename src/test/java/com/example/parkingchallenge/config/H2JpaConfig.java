package com.example.parkingchallenge.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages ="com.example.parkingchallenge" )
@EnableJpaRepositories(basePackages = "com.example.parkingchallenge.repository")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class H2JpaConfig {

}

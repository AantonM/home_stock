package com.homestock.home_stock_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.homestock.home_stock_service.dao"})
public class AppConfiguration
{
}

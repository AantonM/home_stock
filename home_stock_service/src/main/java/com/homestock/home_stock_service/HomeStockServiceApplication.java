package com.homestock.home_stock_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySources({
    @PropertySource(value = "application-default.yaml", ignoreResourceNotFound = true),
    @PropertySource(value = "application.yaml")
})
@SpringBootApplication
public class HomeStockServiceApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(HomeStockServiceApplication.class, args);
  }
}

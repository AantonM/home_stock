package com.homestock.home_stock_service.dao;

import com.homestock.home_stock_service.domain.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long>
{
}

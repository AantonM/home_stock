package com.homestock.home_stock_service.dao;


import com.homestock.home_stock_service.domain.StockMovement;
import org.springframework.data.repository.CrudRepository;

public interface StockMovementRepository extends CrudRepository<StockMovement, Long>
{
}

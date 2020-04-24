package com.homestock.home_stock_service.dao;

import com.homestock.home_stock_service.domain.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Stock, Long>
{
  @Modifying
  @Query(value = "UPDATE homestock.stock SET current_quantity = :quantity WHERE product_id = :productId",  nativeQuery = true)
  int updateStockForProduct(@Param("productId") int productId, @Param("quantity") int quantity);
}

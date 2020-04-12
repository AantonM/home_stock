package com.homestock.home_stock_service.dao;

import com.homestock.home_stock_service.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>
{
}

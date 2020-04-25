package com.homestock.home_stock_service.service;

import com.homestock.home_stock_service.domain.Product;

public interface ProductService
{
    Iterable<Product> getAllProducts();

    void createNewProduct(Product product);
}

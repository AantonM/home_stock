package com.homestock.home_stock_service.service.impl;

import com.homestock.home_stock_service.dao.ProductRepository;
import com.homestock.home_stock_service.domain.Product;
import com.homestock.home_stock_service.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService
{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public void createNewProduct(Product product)
    {
        productRepository.save(product);
    }
}

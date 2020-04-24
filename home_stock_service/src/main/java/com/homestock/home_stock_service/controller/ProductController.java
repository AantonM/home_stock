package com.homestock.home_stock_service.controller;

import com.homestock.home_stock_service.dao.ProductRepository;
import com.homestock.home_stock_service.domain.Product;
import com.homestock.home_stock_service.domain.Stock;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController
{
  ProductRepository productRepository;

  public ProductController(ProductRepository productRepository)
  {
    this.productRepository = productRepository;
  }

  /***
   * Fetch all products.
   * @return - a list of all products
   */
  @GetMapping(path = "/products", produces = "application/json")
  public Iterable<Product> getAllProducts()
  {
    return productRepository.findAll();
  }

  /***
   * Cerate new product.
   * @param product - the product
   */
  @PostMapping(path = "/product", consumes = "application/json")
  public void postNewProduct(@RequestBody Product product)
  {
    productRepository.save(product);
  }

}
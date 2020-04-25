package com.homestock.home_stock_service.controller;

import com.homestock.home_stock_service.dao.ProductRepository;
import com.homestock.home_stock_service.domain.Product;
import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController
{
  ProductService productService;

  public ProductController(ProductService productService)
  {
    this.productService = productService;
  }

  /***
   * Fetch all products.
   * @return - a list of all products
   */
  @GetMapping(path = "/products", produces = "application/json")
  public Iterable<Product> getAllProducts()
  {
    return productService.getAllProducts();
  }

  /***
   * Cerate new product.
   * @param product - the product
   */
  @PostMapping(path = "/product", consumes = "application/json")
  public void createNewProduct(@RequestBody Product product)
  {
    productService.createNewProduct(product);
  }

}
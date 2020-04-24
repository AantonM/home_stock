package com.homestock.home_stock_service.controller;

import com.homestock.home_stock_service.dao.StockRepository;
import com.homestock.home_stock_service.domain.Product;
import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockValue;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "*")
public class StockController
{
  private StockRepository stockRepository;

  public StockController(StockRepository stockRepository)
  {
    this.stockRepository = stockRepository;
  }

  /***
   * Fetch all stocks with links.
   * @return - a list of all Stocks
   */
  @GetMapping(path = "/stocks", produces = "application/json")
  public Iterable<Stock> getAllStocks()
  {
    return stockRepository.findAll();
  }

  /***
   * Create new stock and new product.
   * @param stock - the stock with the new product
   */
  @PostMapping(path = "/stock", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void postNewStock(@RequestBody @Valid Stock stock)
  {
    stockRepository.save(stock);
  }

  /***
   * Create list of new stocks and new products.
   * @param stocks - the stock with the new product
   */
  @PostMapping(path = "/stocks", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void postNewStockList(@RequestBody @Valid List<Stock> stocks)
  {
    stockRepository.saveAll(stocks);
  }

  /***
   * Update stock for existing product.
   * @param productId - the product ID for which the stock should be updated
   * @param stock - the new stock.
   */
  @PutMapping(path = "/update/{productId}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @Transactional
  public void updateStockForProduct(@PathVariable String productId, @RequestBody @Valid StockValue stock)
  {
     int result = stockRepository.updateStockForProduct(Integer.valueOf(productId), stock.getCurrent_quantity());
     if(result == 0)
     {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
     }
  }
}


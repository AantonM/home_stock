package com.homestock.home_stock_service.controller;

import com.homestock.home_stock_service.dao.StockRepository;
import com.homestock.home_stock_service.domain.Product;
import com.homestock.home_stock_service.domain.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping(path = "/stocks", produces = "application/json")
  public Iterable<Stock> getAllStocks()
  {
    return stockRepository.findAll();
  }

  @PostMapping(path = "/stock", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void postNewStock(@RequestBody @Valid Stock stock)
  {
    stockRepository.save(stock);
  }

  @PostMapping(path = "/stocks", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void postNewStockList(@RequestBody @Valid List<Stock> stocks)
  {
    stockRepository.saveAll(stocks);
  }

  @PutMapping(path = "/update/{productId}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @Transactional
  public void updateStockForProduct(@PathVariable String productId, @RequestBody Stock stock)
  {
    stockRepository.updateStockForProduct(Integer.valueOf(productId), stock.getUnit_quantity());
  }

}


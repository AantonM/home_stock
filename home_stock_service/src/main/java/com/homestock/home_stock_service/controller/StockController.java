package com.homestock.home_stock_service.controller;

import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockMovement;
import com.homestock.home_stock_service.service.StockService;
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
    private StockService stockService;

    public StockController(StockService stockService)
    {
        this.stockService = stockService;
    }

    /***
     * Fetch all stocks with links.
     * @return - a list of all Stocks
     */
    @GetMapping(path = "/stocks", produces = "application/json")
    public Iterable<Stock> getAllStocks()
    {
        return stockService.getAllStocks();
    }

    /***
     * Create new stock and new product.
     * @param stock - the stock with the new product
     */
    @PostMapping(path = "/stock", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewStock(@RequestBody @Valid Stock stock)
    {
        stockService.createNewStockAndProduct(stock);
    }

    /***
     * Create list of new stocks and new products.
     * @param stocks - the stock with the new product
     */
    @PostMapping(path = "/stocks", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewStockList(@RequestBody @Valid List<Stock> stocks)
    {
        stockService.createNewStocksAndProducts(stocks);
    }

    /***
     * Increase the stock quantity of a given product.
     * @param productId - the product ID
     * @param stockMovement - the increase stock movement
     */
    @PutMapping(path = "/stock/increase/{productId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void increaseStockForProduct(@PathVariable Long productId, @RequestBody @Valid StockMovement stockMovement)
    {
        stockService.increaseStock(productId, stockMovement);
    }

    /***
     * Decrease the stock quantity of a given product.
     * @param productId - the product ID
     * @param stockMovement - the decreasing stock movement
     */
    @PutMapping(path = "/stock/decrease/{productId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void decreaseStockForProduct(@PathVariable Long productId, @RequestBody @Valid StockMovement stockMovement)
    {
        stockService.decreaseStock(productId, stockMovement);
    }
}


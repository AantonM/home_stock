package com.homestock.home_stock_service.service;

import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockMovement;

import java.util.List;

public interface StockService
{
    Iterable<Stock> getAllStocks();

    void createNewStockAndProduct(Stock stock);

    void createNewStocksAndProducts(List<Stock> stock);

    void increaseStock(Long productId, StockMovement stockValue);

    void decreaseStock(Long productId, StockMovement stockValue);

    void updateStock(Long productId, StockMovement stockMovement);
}

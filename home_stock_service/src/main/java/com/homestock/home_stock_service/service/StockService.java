package com.homestock.home_stock_service.service;

import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockValue;

import java.util.List;

public interface StockService
{
    Iterable<Stock> getAllStocks();

    void createNewStockAndProduct(Stock stock);

    void createNewStocksAndProducts(List<Stock> stock);

    void updateStockOfProduct(String productId, StockValue stockValue);
}

package com.homestock.home_stock_service.service.impl;

import com.homestock.home_stock_service.dao.StockRepository;
import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockValue;
import com.homestock.home_stock_service.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StockServiceImpl implements StockService
{

    private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository)
    {
        this.stockRepository = stockRepository;
    }

    @Override
    public Iterable<Stock> getAllStocks()
    {
        return stockRepository.findAll();
    }

    @Override
    public void createNewStockAndProduct(Stock stock)
    {
        stockRepository.save(stock);
    }

    @Override
    public void createNewStocksAndProducts(List<Stock> stocks)
    {
        stockRepository.saveAll(stocks);
    }

    @Override
    public void updateStockOfProduct(String productId, StockValue stockValue)
    {
        int result = stockRepository.updateStockForProduct(Integer.valueOf(productId), stockValue.getCurrent_quantity());
        if(result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
    }
}

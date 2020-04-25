package com.homestock.home_stock_service.service.impl;

import com.homestock.home_stock_service.dao.StockMovementRepository;
import com.homestock.home_stock_service.dao.StockRepository;
import com.homestock.home_stock_service.domain.Stock;
import com.homestock.home_stock_service.domain.StockMovement;
import com.homestock.home_stock_service.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService
{

    private StockRepository stockRepository;
    private StockMovementRepository stockMovementRepository;

    public StockServiceImpl(StockRepository stockRepository, StockMovementRepository stockMovementRepository)
    {
        this.stockRepository = stockRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public Iterable<Stock> getAllStocks()
    {
        return stockRepository.findAll();
    }

    @Override
    public void createNewStockAndProduct(Stock stock)
    {
        Stock createdStock = stockRepository.save(stock);
        StockMovement stockMvnt = buildStockMovement(createdStock);
        stockMovementRepository.save(stockMvnt);
    }

    @Override
    public void createNewStocksAndProducts(List<Stock> stocks)
    {
        List<Stock> createdStocks = (List<Stock>) stockRepository.saveAll(stocks);
        createdStocks.stream()
                .map(n -> buildStockMovement(n))
                .forEach(e -> stockMovementRepository.save(e));
    }

    @Override
    public void increaseStock(Long productId, StockMovement stockValue)
    {
        Stock stockToUpdate = stockRepository.findStockByProductId(Long.valueOf(productId));

        int result = stockRepository.increaseStockForProduct(productId, stockValue.getQuantity());
        if (result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
        stockMovementRepository.save(buildStockMovementForUpdate(stockToUpdate, stockValue, true, false));
    }

    @Override
    public void decreaseStock(Long productId, StockMovement stockValue)
    {
        Stock stockToUpdate = stockRepository.findStockByProductId(Long.valueOf(productId));

        int result = stockRepository.decreaseStockForProduct(productId, stockValue.getQuantity());
        if (result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
        stockMovementRepository.save(buildStockMovementForUpdate(stockToUpdate, stockValue, false, true));
    }

    private StockMovement buildStockMovementForUpdate(Stock stock, StockMovement stockMovement, boolean up, boolean down)
    {
        stockMovement.setStock(stock);
        stockMovement.setDate(new Date(System.currentTimeMillis()));
        stockMovement.setUp(up);
        stockMovement.setDown(down);
        return stockMovement;
    }

    private StockMovement buildStockMovement(Stock stock)
    {
        StockMovement stockmvmnt = new StockMovement();
        stockmvmnt.setStock(stock);
        stockmvmnt.setQuantity(stock.getCurrent_quantity());
        stockmvmnt.setDate(new Date(System.currentTimeMillis()));
        stockmvmnt.setUp(true);
        stockmvmnt.setDown(false);
        return stockmvmnt;
    }
}

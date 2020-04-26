package com.homestock.home_stock_service.service.impl;

import com.homestock.home_stock_service.dao.StockMovementConstants;
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

import static com.homestock.home_stock_service.dao.StockMovementConstants.*;

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
    public void increaseStock(Long productId, StockMovement stockMovement)
    {
        Stock stockToUpdate = stockRepository.findStockByProductId(Long.valueOf(productId));

        int result = stockRepository.increaseStockForProduct(productId, stockMovement.getQuantity());
        if (result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
        stockMovementRepository.save(buildStockMovementForUpdate(stockToUpdate, stockMovement, STOCK_MOVEMENT_UP));
    }

    @Override
    public void decreaseStock(Long productId, StockMovement stockMovement)
    {
        Stock stockToUpdate = stockRepository.findStockByProductId(Long.valueOf(productId));

        int result = stockRepository.decreaseStockForProduct(productId, stockMovement.getQuantity());
        if (result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
        stockMovementRepository.save(buildStockMovementForUpdate(stockToUpdate, stockMovement, STOCK_MOVEMENT_DOWN));
    }

    @Override
    public void updateStock(Long productId, StockMovement stockMovement)
    {
        Stock stockToUpdate = stockRepository.findStockByProductId(Long.valueOf(productId));

        int result = stockRepository.updateStockForProduct(productId, stockMovement.getQuantity());
        if (result == 0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
        stockMovementRepository.save(buildStockMovementForUpdate(stockToUpdate, stockMovement, STOCK_MOVEMENT_OVERRIDE));
    }

    private StockMovement buildStockMovementForUpdate(Stock stock, StockMovement stockMovement, Integer type)
    {
        stockMovement.setStock(stock);
        stockMovement.setDate(new Date(System.currentTimeMillis()));
        stockMovement.setType(type);
        return stockMovement;
    }

    private StockMovement buildStockMovement(Stock stock)
    {
        StockMovement stockmvmnt = new StockMovement();
        stockmvmnt.setStock(stock);
        stockmvmnt.setQuantity(stock.getCurrent_quantity());
        stockmvmnt.setDate(new Date(System.currentTimeMillis()));
        stockmvmnt.setType(STOCK_MOVEMENT_UP);
        return stockmvmnt;
    }
}

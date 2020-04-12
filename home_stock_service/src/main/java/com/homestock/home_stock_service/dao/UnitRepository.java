package com.homestock.home_stock_service.dao;

import com.homestock.home_stock_service.domain.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long>
{
}

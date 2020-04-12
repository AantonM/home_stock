package com.homestock.home_stock_service.domain.validator;

import com.homestock.home_stock_service.dao.UnitRepository;
import com.homestock.home_stock_service.domain.Unit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class UnitValidator implements ConstraintValidator<ValidUnit, Unit>
{
  UnitRepository unitRepository;

  public UnitValidator(UnitRepository unitRepository)
  {
    this.unitRepository = unitRepository;
  }

  @Override
  public void initialize(ValidUnit constraintAnnotation)
  {
    //Do nothing
  }

  /**
   * Validate that the given unit exist in the database. Verify on unit name.
   * And update the provided unitId with the one in the database.
   *
   * @param unit                       - the given unit
   * @param constraintValidatorContext
   * @return true: if unit exists in the database, false: if the unit does not exist in the database.
   */
  @Override
  public boolean isValid(Unit unit, ConstraintValidatorContext constraintValidatorContext)
  {
    Iterable<Unit> iterable = () -> unitRepository.findAll().iterator();
    Optional dbUnit = StreamSupport.stream(iterable.spliterator(), false).filter(e -> e.getName().equals(unit.getName())).findFirst();

    if (dbUnit.isPresent())
    {
      unit.setId(((Unit) dbUnit.get()).getId());
      return true;
    }
    else
    {
      return false;
    }
  }

}

package com.homestock.home_stock_service.domain.validator;

import com.homestock.home_stock_service.dao.UnitRepository;
import com.homestock.home_stock_service.domain.Unit;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class UnitValidator implements ConstraintValidator<ValidUnit, Unit>
{
  UnitRepository unitRepository;

  public UnitValidator(UnitRepository unitRepository)
  {
    this.unitRepository = unitRepository;
  }

  public void initialize(ValidUnit constraintAnnotation)
  {
  }

  /**
   * Validte that the given unit exist in the database. Verify on unit name.
   *
   * @param unit - the given unit
   * @param constraintValidatorContext
   * @return true: if unit exists in the database, false: if the unit does not exist in the database.
   */
  public boolean isValid(Unit unit, ConstraintValidatorContext constraintValidatorContext)
  {
      Iterable<Unit> iterable = () -> unitRepository.findAll().iterator();
      Stream<Unit> targetStream = StreamSupport.stream(iterable.spliterator(), false);
      return targetStream.filter(e-> e.getName().equals(unit.getName())).findFirst().isPresent();
  }

}

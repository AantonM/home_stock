package com.homestock.home_stock_service.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DaoExceptionHandler extends ResponseEntityExceptionHandler
{

    /***
     * Customer Exception Handler for DataIntegrityViolationException, which will
     * return a BAD_REQUEST response instead of database log.
     * @param ex - the exception
     * @return - BAD_REQUEST response
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityException(DataIntegrityViolationException ex)
    {
        return new ResponseEntity<>("Message Body is Invalid", HttpStatus.BAD_REQUEST);
    }
}

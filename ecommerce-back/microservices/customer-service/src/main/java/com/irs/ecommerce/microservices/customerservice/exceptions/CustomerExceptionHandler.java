package com.irs.ecommerce.microservices.customerservice.exceptions;

import com.irs.ecommerce.microservices.commonexceptions.ErrorResponse;
import com.irs.ecommerce.microservices.commonexceptions.GlobalExceptionHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Maneja las exceptiones propias del servicio de Customer.
 */
@RestControllerAdvice(basePackages = "com.irs.ecommerce.microservices.customerservice")
@Primary
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        Map<String,String> errors = new HashMap<>();
        String fieldName = "customer";
        String errorMessage = exception.getMessage();
        errors.put(fieldName, errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}

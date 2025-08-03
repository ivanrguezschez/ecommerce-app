package com.irs.ecommerce.microservices.productservice.exceptions;

import com.irs.ecommerce.microservices.commonexceptions.ErrorResponse;
import com.irs.ecommerce.microservices.commonexceptions.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.irs.ecommerce.microservices.productservice")
@Primary
@Slf4j
public class ProductExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorResponse> handle (CategoryException exception) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "product-service";
        errors.put(fieldName, exception.getMessage());

        log.warn("Category error: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> handle (ProductException exception) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "product-service";
        errors.put(fieldName, exception.getMessage());

        log.warn("Product error: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}

package com.irs.ecommerce.microservices.cartservice.exceptions;

import com.irs.ecommerce.microservices.commonexceptions.ErrorResponse;
import com.irs.ecommerce.microservices.commonexceptions.GlobalExceptionHandler;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.irs.ecommerce.microservices.cartservice")
@Primary
@Slf4j
public class CartExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorResponse> handleCartException(CartException exception) {
        Map<String,String> errors = new HashMap<>();
        String fieldName = "cart";

        errors.put(fieldName, exception.getMessage());

        log.warn("Cart error: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException exception) {
        Map<String,String> errors = new HashMap<>();
        String fieldName = "Error communicating with microservice";

        errors.put(fieldName, exception.getMessage());

        log.warn("Error communicating with microservice: {}", exception.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}

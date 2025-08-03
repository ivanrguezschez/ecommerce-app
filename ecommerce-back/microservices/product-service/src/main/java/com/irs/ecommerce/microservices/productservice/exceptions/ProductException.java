package com.irs.ecommerce.microservices.productservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductException extends RuntimeException {
    private final String message;
}

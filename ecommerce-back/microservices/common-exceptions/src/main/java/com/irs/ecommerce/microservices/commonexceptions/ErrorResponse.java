package com.irs.ecommerce.microservices.commonexceptions;

import java.util.Map;

public record ErrorResponse(Map<String,String> error) {
}

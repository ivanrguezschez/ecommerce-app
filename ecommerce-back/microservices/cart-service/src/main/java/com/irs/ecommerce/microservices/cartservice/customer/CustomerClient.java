package com.irs.ecommerce.microservices.cartservice.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{customerId}")
    Optional<CustomerResponseDTO> getCustomerById(@PathVariable("customerId") String customerId);

}

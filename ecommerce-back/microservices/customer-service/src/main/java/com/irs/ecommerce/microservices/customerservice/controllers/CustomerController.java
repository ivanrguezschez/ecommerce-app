package com.irs.ecommerce.microservices.customerservice.controllers;

import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;
import com.irs.ecommerce.microservices.customerservice.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return ResponseEntity.ok(this.customerService.getCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        //return ResponseEntity.ok(this.customerService.save(customerRequestDTO));
        return new ResponseEntity<>(this.customerService.save(customerRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        this.customerService.save(customerRequestDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        this.customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}

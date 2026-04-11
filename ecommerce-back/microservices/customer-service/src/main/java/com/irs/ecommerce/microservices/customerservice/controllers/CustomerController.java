package com.irs.ecommerce.microservices.customerservice.controllers;

import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;
import com.irs.ecommerce.microservices.customerservice.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obtiene todos los clientes", description = "Recupera todos los clientes.")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return ResponseEntity.ok(this.customerService.getCustomers());
    }

    @Operation(summary = "Obtiene un cliente específico", description = "Recupera un cliente específico.")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }

    @Operation(summary = "Crea un nuevo producto", description = "Inserta un nuevo producto.")
    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        //return ResponseEntity.ok(this.customerService.save(customerRequestDTO));
        return new ResponseEntity<>(this.customerService.save(customerRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un cliente específico", description = "Actualiza un cliente específico.")
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        this.customerService.save(customerRequestDTO);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Elimina un cliente específico", description = "Borra un cliente específico.")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        this.customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}

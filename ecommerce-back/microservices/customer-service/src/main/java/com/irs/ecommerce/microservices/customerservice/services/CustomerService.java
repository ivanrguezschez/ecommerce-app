package com.irs.ecommerce.microservices.customerservice.services;

import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;


import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> getCustomers();

    CustomerResponseDTO getCustomerById(String customerId);

    String save(CustomerRequestDTO customerRequestDTO);

    void deleteById(String customerId);
}

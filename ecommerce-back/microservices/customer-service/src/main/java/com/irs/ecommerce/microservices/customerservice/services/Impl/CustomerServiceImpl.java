package com.irs.ecommerce.microservices.customerservice.services.Impl;

import com.irs.ecommerce.microservices.customerservice.models.collections.Customer;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;
import com.irs.ecommerce.microservices.customerservice.repositories.CustomerRepository;
import com.irs.ecommerce.microservices.customerservice.services.CustomerService;
import com.irs.ecommerce.microservices.customerservice.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponseDTO> getCustomers() {
        return this.customerRepository.findAll().stream()
                .map(this.customerMapper::toCustomerResponse)
                .toList();
    }

    public CustomerResponseDTO getCustomerById(String customerId) {
        return this.customerRepository.findById(customerId)
                .map(this.customerMapper::toCustomerResponse)
                .orElseThrow();
    }

    public String save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = this.customerMapper.toCustomer(customerRequestDTO);
        Customer customerSaved = this.customerRepository.save(customer);

        return customerSaved.getId();
    }

    public void deleteById(String customerId) {
        // Si no encuentra el cliente lanza una excepcion
        this.customerRepository.findById(customerId).orElseThrow();

        this.customerRepository.deleteById(customerId);
    }
}

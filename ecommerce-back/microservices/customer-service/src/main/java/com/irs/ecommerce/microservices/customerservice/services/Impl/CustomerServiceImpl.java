package com.irs.ecommerce.microservices.customerservice.services.Impl;

import com.irs.ecommerce.microservices.customerservice.exceptions.CustomerNotFoundException;
import com.irs.ecommerce.microservices.customerservice.models.collections.Customer;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;
import com.irs.ecommerce.microservices.customerservice.repositories.CustomerRepository;
import com.irs.ecommerce.microservices.customerservice.services.CustomerService;
import com.irs.ecommerce.microservices.customerservice.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", customerId)));
    }

    public String save(CustomerRequestDTO customerRequestDTO) {
        if (StringUtils.hasText(customerRequestDTO.id())) {
            // Si es una actualizacion debe venir y existir el customer a actualizar, si no encuentra el cliente lanza una excepcion
            this.customerRepository.findById(customerRequestDTO.id())
                    .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", customerRequestDTO.id())));
        }

        Customer customer = this.customerMapper.toCustomer(customerRequestDTO);
        Customer customerSaved = this.customerRepository.save(customer);

        return customerSaved.getId();
    }

    public void deleteById(String customerId) {
        // Si no encuentra el cliente lanza una excepcion
        this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", customerId)));

        this.customerRepository.deleteById(customerId);
    }
}

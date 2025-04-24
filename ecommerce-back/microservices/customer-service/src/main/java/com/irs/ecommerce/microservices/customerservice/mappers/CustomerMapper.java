package com.irs.ecommerce.microservices.customerservice.mappers;

import com.irs.ecommerce.microservices.customerservice.models.collections.Customer;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerRequestDTO;
import com.irs.ecommerce.microservices.customerservice.models.dtos.CustomerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDTO customerRequestDTO) {
        return Customer.builder()
                .id(customerRequestDTO.id())
                .firstName(customerRequestDTO.firstName())
                .lastName(customerRequestDTO.lastName())
                .email(customerRequestDTO.email())
                .phone(customerRequestDTO.phone())
                .address(customerRequestDTO.address())
                .city(customerRequestDTO.city())
                .build();
    }

    public CustomerResponseDTO toCustomerResponse(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .city(customer.getCity())
                .build();
    }
}

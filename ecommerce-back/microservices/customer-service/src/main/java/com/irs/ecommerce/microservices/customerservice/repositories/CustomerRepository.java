package com.irs.ecommerce.microservices.customerservice.repositories;

import com.irs.ecommerce.microservices.customerservice.models.collections.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}

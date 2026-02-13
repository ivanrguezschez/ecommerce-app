package com.irs.ecommerce.microservices.cartservice.customer;

import lombok.Builder;

@Builder
public record CustomerResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        String city
) {
}

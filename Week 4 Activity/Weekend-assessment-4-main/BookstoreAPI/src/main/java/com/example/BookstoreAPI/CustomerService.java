package com.example.BookstoreAPI;

import java.util.Optional;

public class CustomerService {
	
private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}

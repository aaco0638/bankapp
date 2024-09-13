package com.example.final_project.service;

import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Customer;

import java.util.List;

public interface CustomerService {
    public CustomerDTO registerCustomer(String fullName);
    public CustomerDTO getCustomerById(Long customerId);
    public CustomerDTO convertToCustomerDTO(Customer customer);
    public double deleteCustomer(Long customerId);
    public List<CustomerDTO> findAllCustomers();
}

package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplm implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //CREATE LOGIN METHOD? IF SO, UPDATE CUSTOMERSERVICETEST



    @Transactional
    public CustomerDTO registerCustomer(String fullName) {
        Customer customer = new Customer(fullName);
        customerRepository.save(customer);
        return convertToCustomerDTO(customer);
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return convertToCustomerDTO(customer);
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        // Corrected to get List<Integer> instead of List<AccountDTO>
        List<Integer> accountNumbers = customer.getAccounts() != null ?
                customer.getAccounts().stream()
                        .map(Account::getAccountNumber)
                        .collect(Collectors.toList()) :
                List.of();

        return new CustomerDTO(customer.getCustomerId(), customer.getFullName(), accountNumbers);
    }

    @Transactional
    public double deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found."));

        double totalFunds = customer.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();

        customerRepository.delete(customer);

        return totalFunds;
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }
}

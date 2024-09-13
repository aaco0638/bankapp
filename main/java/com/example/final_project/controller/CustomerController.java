//package com.example.final_project.controller;
//
//import com.example.final_project.dto.RegisterCustomerDto;
//import com.example.final_project.dto.CustomerDTO;
//import com.example.final_project.entities.Customer;
//import com.example.final_project.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/customer")
//@CrossOrigin
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    // Retrieve all Customers
//    @GetMapping
//    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
//        List<CustomerDTO> Customers = customerService.findAllCustomers();
//        return ResponseEntity.ok(Customers);
//    }
//
//    // Retrieve a Customer by Customer ID
//    @GetMapping("/{CustomerId}")
//    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long CustomerId) {
//        CustomerDTO Customer = customerService.getCustomerById(CustomerId);
//        return ResponseEntity.ok(Customer);
//    }
//
//    @PostMapping
//    public ResponseEntity<Customer> register(@RequestBody RegisterCustomerDto Customer) {
//        Customer newCustomer = customerService.registerDTO(Customer.getName());
//        return ResponseEntity.status(201).body(newCustomer);
//    }
//
//    // Delete a Customer by Customer ID
//    @DeleteMapping("/{CustomerId}")
//    public ResponseEntity<Double> deleteCustomer(@PathVariable Long CustomerId) {
//        double totalFunds = customerService.deleteCustomer(CustomerId);
//        return ResponseEntity.ok(totalFunds);
//    }
//}



package com.example.final_project.controller;

import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerNumber) {
        try {
            CustomerDTO customer = customerService.getCustomerById(customerNumber);
            return ResponseEntity.ok(customer);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(null); // Corrected to getStatusCode()
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody String fullName) {
        CustomerDTO newCustomer = customerService.registerCustomer(fullName);
        return ResponseEntity.status(201).body(newCustomer);
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<Double> deleteCustomer(@PathVariable Long customerNumber) {
        try {
            double totalFunds = customerService.deleteCustomer(customerNumber);
            return ResponseEntity.ok(totalFunds);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(null); // Corrected to getStatusCode()
        }
    }
}

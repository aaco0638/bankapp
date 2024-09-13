package com.example.final_project.service;

import com.example.final_project.dto.AccountDTO;
import com.example.final_project.dto.CreateAccountRequestDTO;
import com.example.final_project.dto.CreateAccountResponseDto;
import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplm implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public AccountDTO getAccountByNumber(int accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return new AccountDTO(
                account.getAccountNumber(),
                account.getSortCode(),
                account.getAccountName(),
                account.getBalance(),
                new ArrayList<TransactionDTO>(),
                account.getBalance(),
                account.getCustomer().getCustomerId());
    }

    public CreateAccountResponseDto createAccount(CreateAccountRequestDTO requestDto) {
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found."));

        Account account = new Account();
        account.setAccountName(requestDto.getAccountName());
        account.setAccountType("Default"); // Or assign based on logic
        account.setBalance(requestDto.getOpeningBalance());
        account.setCustomer(customer);
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setSortCode(1234); // Default sort code for the bank

        accountRepository.save(account);

        return new CreateAccountResponseDto(
                account.getAccountNumber(),
                account.getSortCode(),
                account.getAccountName(),
                account.getBalance(),
                customer.getCustomerId());
    }

    public double deleteAccount(Integer accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Number not found."));//RuntimeException("Account not found"));
        double finalBalance = account.getBalance();
        accountRepository.delete(account);
        return finalBalance;

    }

    public int generateUniqueAccountNumber() {
        // Implement logic to generate unique account numbers
        return (int) (Math.random() * 1000000000);
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> new AccountDTO(

                        account.getAccountNumber(),
                        account.getSortCode(),
                        account.getAccountName(),
                        account.getBalance(),
                        new ArrayList<TransactionDTO>(),
                        account.getBalance(),
                        account.getCustomer().getCustomerId()))

                .collect(Collectors.toList());
    }
}

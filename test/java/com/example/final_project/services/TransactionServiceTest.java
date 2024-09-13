package com.example.final_project.services;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Customer;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    //Commented out Transaction and TransactionDTO because they are data objects and not dependencies and dont provide services or components? FOLLOW UP WITH TEAM TO KEEP OR REMOVE
//    private Transaction;
//    private TransactionDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessDepositTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Account toAccount = new Account(1234,123456,"CK Checking Account","CHECKING",1000.00, customer1);

        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(2,1234,null,12345678, null,500.00);
        when(accountRepository.findByAccountNumber((1234))).thenReturn(Optional.of(toAccount));
        //Transaction savedTransaction = transactionService.processTransaction(TransactionRequestDTO);

        //Test method processTransaction in TransactionService class
        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(transactionrequestDTO);
        assertEquals(500.00, toAccount.getBalance());
        verify(accountRepository).save(toAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }

    @Test
    public void testProcessWithdrawalTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Account fromAccount = new Account(1234,123456,"CK Checking Account","SAVINGS",5000.00, customer1);

        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(1,2000,null,null, null,4000.00);
        when(accountRepository.findByAccountNumber(1234)).thenReturn(Optional.of(fromAccount));
        //Transaction savedTransaction = transactionService.processTransaction(TransactionRequestDTO);

        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(result);
        assertEquals(4000, fromAccount.getBalance());
        verify(accountRepository).save(fromAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }

    @Test
    public void testProcessTransferTransaction(){
        Customer customer1 = new Customer("CK Boss");
        Customer customer2 = new Customer("Tiego");

        Account fromAccount = new Account(1234,123456,"CK Checking Account","CHECKING",5000.00, customer1);
        Account toAccount = new Account(4321,123456,"Tiego Savings Account","SAVINGS",10000.00, customer2);

        TransactionRequestDTO transactionrequestDTO = new TransactionRequestDTO(0,1234,123456,4321, 123456,5.00);
        when(accountRepository.findByAccountNumber(1234)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(4321)).thenReturn(Optional.of(toAccount));

        TransactionDTO result = transactionService.processTransaction(transactionrequestDTO);

        assertNotNull(result);
        assertEquals(4995.00, fromAccount.getBalance());
        assertEquals(10005.00, toAccount.getBalance());
        verify(accountRepository).save(fromAccount);
        verify(accountRepository).save(toAccount);
        verify(transactionRepository).save(any(Transaction.class));

    }

}

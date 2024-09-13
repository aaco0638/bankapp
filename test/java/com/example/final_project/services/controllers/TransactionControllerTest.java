package com.example.final_project.controllers;
import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import com.example.final_project.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private TransactionRequestDTO requestDTO;
    private TransactionDTO transactionDTO;
    private TransactionService transactionService;

    @BeforeEach
    void setup() {

        requestDTO = new TransactionRequestDTO();



    }


    @Test
    public void testCreateTransactionInsufficientTransferFunds() throws Exception {


        Account fromAccount = new Account();
        fromAccount.setAccountNumber(1);
        fromAccount.setBalance(50.0);
        Account toAccount = new Account();
        toAccount.setAccountNumber(2);
        toAccount.setBalance(0.0);
        // Mock AccountRepository behavior
        when(accountRepository.findByAccountNumber(1)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(2)).thenReturn(Optional.of(toAccount));
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setType(0); // Transfer
        requestDTO.setFromAccount(1);
        requestDTO.setToAccount(2);
        requestDTO.setAmount(100.0); // Insufficient funds

        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void testCreateTransactionInvalidType() throws Exception {

        // Create TransactionRequestDTO with an invalid type
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setType(99);



        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError());


    }

    @Test
    public void testCreateTransactionInsufficientWithdrawFunds() throws Exception {
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(1);
        fromAccount.setBalance(50.0);
        // Mock AccountRepository behavior
        when(accountRepository.findByAccountNumber(1)).thenReturn(Optional.of(fromAccount));
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setType(1); // Withdraw
        requestDTO.setFromAccount(1);
        requestDTO.setAmount(200.0); // Insufficient funds

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateTransactionInsufficientDepositFunds() throws Exception {
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(1);
        fromAccount.setBalance(0.0);
        // Mock AccountRepository behavior
        when(accountRepository.findByAccountNumber(1)).thenReturn(Optional.of(fromAccount));
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setType(2); // Deposit
        requestDTO.setFromAccount(1);
        requestDTO.setAmount(200.0); // Insufficient funds

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError());
    }

}







 /*
            Create from account with $50 -> id 1
            Create to account with $0 -> id 2
            add when account repo get by id 1 return from account
            add when account repo get by id 2 return to account
            create transaction request dto transferring $100 from account to account

            use mvcMock to post request to transaction end point

            expect 500 error
         */


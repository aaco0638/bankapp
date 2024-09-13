//package com.example.final_project.services;
//
//import com.example.final_project.dto.AccountDTO;
//import com.example.final_project.entities.Account;
//import com.example.final_project.repository.AccountRepository;
//import com.example.final_project.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.security.auth.login.AccountNotFoundException;
//
//public class AccountServiceTest {
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountService accountService;
//    private Account account;
//    private AccountDTO accountDTO;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//
//        account = new Account(12345678,1234,);
//        accountDTO = new AccountDTO(12345678,1234,"Kat",500.00,1000.00);
//    }
//
//    @Test
//    public void testGetAccountByNumber() throws AccountNotFoundException{
//
//    }
//    @Test
//    public void testCreateAccount() throws AccountNotFoundException{
//
//    }
//
//    @Test
//    public void testDeleteAccount() throws AccountNotFoundException{
//
//    }
//
//    @Test
//    public void testGetAllAccounts() throws AccountNotFoundException{
//
//    }
//
//
//
//
//}

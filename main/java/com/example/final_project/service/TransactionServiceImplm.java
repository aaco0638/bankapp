package com.example.final_project.service;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Account;
import com.example.final_project.entities.Transaction;
import com.example.final_project.repository.AccountRepository;
import com.example.final_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImplm implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionDTO processTransaction(TransactionRequestDTO request) {
        // Map the transaction type based on the request
        String transactionType = switch (request.getType()) {
            case 2 -> "DEPOSIT";
            case 1 -> "WITHDRAWAL";
            case 0 -> "TRANSFER";
            default -> throw new IllegalArgumentException("Invalid transaction type: " + request.getType());
        };

        if (request.getToAccount() != null && request.getToAccount() <= 0) {
            throw new IllegalArgumentException("Invalid 'To' account number: " + request.getToAccount());
        }

        Optional<Account> fromAccountOpt = Optional.empty();
        if (request.getFromAccount() != null) {
            fromAccountOpt = accountRepository.findByAccountNumber(request.getFromAccount());
            if (fromAccountOpt.isEmpty()) {
                throw new IllegalArgumentException("From account not found with account number: " + request.getFromAccount());
            }
        }

        Optional<Account> toAccountOpt = Optional.empty();
        if (request.getToAccount() != null) {
            toAccountOpt = accountRepository.findByAccountNumber(request.getToAccount());
            if (toAccountOpt.isEmpty()) {
                throw new IllegalArgumentException("To account not found with account number: " + request.getToAccount());
            }
        }

        switch (transactionType) {
            case "DEPOSIT" -> {
                if (toAccountOpt.isPresent()) {
                    Account toAccount = toAccountOpt.get();
                    toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                    accountRepository.save(toAccount);
                } else {
                    throw new IllegalArgumentException("To account not found for DEPOSIT operation.");
                }
            }
            case "WITHDRAWAL" -> {
                if (fromAccountOpt.isPresent()) {
                    Account fromAccount = fromAccountOpt.get();
                    if (fromAccount.getBalance() < request.getAmount()) {
                        throw new IllegalArgumentException("Insufficient funds in the from account: " + request.getFromAccount());
                    }
                    fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                    accountRepository.save(fromAccount);
                } else {
                    throw new IllegalArgumentException("From account not found for WITHDRAWAL operation.");
                }
            }
            case "TRANSFER" -> {
                if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
                    Account fromAccount = fromAccountOpt.get();
                    Account toAccount = toAccountOpt.get();
                    if (fromAccount.getBalance() < request.getAmount()) {
                        throw new IllegalArgumentException("Insufficient funds in the from account: " + request.getFromAccount());
                    }
                    fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                    toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                    accountRepository.save(fromAccount);
                    accountRepository.save(toAccount);
                } else {
                    throw new IllegalArgumentException("From or To account not found for TRANSFER operation.");
                }
            }
            default -> throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(request.getFromAccount());
        transaction.setToAccountNumber(request.getToAccount());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(transactionType);
        transaction.setTimestamp(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        return convertToTransactionDTO(transaction);
    }

    public TransactionDTO convertToTransactionDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getTransactionType(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getAmount(),
                transaction.getTimestamp()
        );
    }
}

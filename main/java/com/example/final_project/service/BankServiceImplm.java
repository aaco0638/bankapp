package com.example.final_project.service;

import com.example.final_project.entities.BankInfo;
import com.example.final_project.repository.BankInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImplm implements BankService {

    @Autowired
    private BankInfoRepository bankInfoRepository;

    public String getSortCode() {
        BankInfo bankInfo = bankInfoRepository.findFirstByOrderByIdAsc();
        if (bankInfo != null) {
            return bankInfo.getSortCode();
        } else {
            throw new RuntimeException("Sort code not found");
        }
    }
}

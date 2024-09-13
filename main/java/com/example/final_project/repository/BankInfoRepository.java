package com.example.final_project.repository;

import com.example.final_project.entities.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
    BankInfo findFirstByOrderByIdAsc();
}

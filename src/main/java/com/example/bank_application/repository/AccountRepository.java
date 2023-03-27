package com.example.bank_application.repository;

import com.example.bank_application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountById(UUID id);

//    List<Account> getAllAccounts();
}

package com.example.bank_application.repository;

import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findClientByTaxCode(String taxCode);

    List<Client> findByAccounts_BalanceGreaterThanEqualAndAccounts_CurrencyCode(Double balance, CurrencyType currency);
}


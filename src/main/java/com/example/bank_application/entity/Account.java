package com.example.bank_application.entity;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.AccountType;
import com.example.bank_application.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account  {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)

    @Column(name = "id", updatable=false, nullable=false)
    private UUID id;

    @Column(name = "name", updatable=false, nullable=false)
    private String name;

    @Column(name = "type", updatable=false, nullable=false)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "balance", nullable=false)
    private Double balance;

    @Column(name = "currency_code", updatable=false, nullable=false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyCode;

    @Column(name = "created_at", updatable=false, nullable=false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(optional=false)
    @JoinColumn(name = "client_id", referencedColumnName="id")
    private Client client;

    @OneToMany(mappedBy = "debitAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactionDebits;

    @OneToMany(mappedBy = "creditAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactionCredits;

    @OneToOne(optional=false, mappedBy = "account", cascade = CascadeType.ALL)
    private Agreement agreement;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

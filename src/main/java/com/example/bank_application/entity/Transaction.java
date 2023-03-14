package com.example.bank_application.entity;

import com.example.bank_application.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
//    @GenericGenerator(name = "UUID",
//            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "amount")
    private double amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_account_id", referencedColumnName="id")
    private Account debitAccount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_account_id", referencedColumnName="id")
    private Account creditAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && description.equals(that.description) && createdAt.equals(that.createdAt) && debitAccount.equals(that.debitAccount) && creditAccount.equals(that.creditAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, createdAt, debitAccount, creditAccount);
    }
}

package com.example.bank_application.entity;

import com.example.bank_application.entity.enums.AccountProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.Timestamp;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "UUID",
//            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private int id;
    @Column(name = "interest_rate")
    private float interestRate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountProductStatus status;
    @Column(name = "sum")
    private double sum;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName="id")
    private Account account;
    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName="id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return id == agreement.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

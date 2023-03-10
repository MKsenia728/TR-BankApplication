package com.example.bank_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.security.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "interest_rate")
    private float interestRate;
    @Column(name = "status")
    private boolean status;
    @Column(name = "sum")
    private double sum;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "accounts_id", referencedColumnName="id")
    private Account account;
    @ManyToOne()
    @JoinColumn(name = "products_id", referencedColumnName="id")
    private Product product;

}

package com.example.bank_application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.security.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
//    @GenericGenerator(name = "UUID",
//            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")

    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private boolean type;
    @Column(name = "status")
    private boolean status;
    @Column(name = "balance")
    private double balance;
    @Column(name = "currency_code")
    private byte currencyCode;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName="id")
    private Client client;
    @OneToMany(mappedBy = "debitAccount", cascade = CascadeType.ALL)
    private Set<Transaction> transactionDebits;
    @OneToMany(mappedBy = "creditAccount", cascade = CascadeType.ALL)
    private Set<Transaction> transactionCredits;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Agreement> agreements;
}

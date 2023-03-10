package com.example.bank_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.security.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private boolean type;
    @Column(name = "status")
    private boolean status;
    @Column(name = "currency_code")
    private byte currencyCode;
    @Column(name = "interest_rate")
    private float interestRate;
    @Column(name = "limit")
    private int limit;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Agreement> agreement;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="manager_id", referencedColumnName = "id")
    private Set<Manager> manager;
}

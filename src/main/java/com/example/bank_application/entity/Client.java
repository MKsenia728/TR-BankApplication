package com.example.bank_application.entity;

import com.example.bank_application.entity.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "UUID",
//            strategy = "com.example.bank_application.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    @Column(name = "tax_code")
    private String taxCode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Account> accounts;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName="id")
    private Manager manager;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return taxCode.equals(client.taxCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxCode);
    }
}

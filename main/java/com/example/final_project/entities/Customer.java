//package com.example.final_project.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "account_customer", schema = "banking")
//@Data
//public class Customer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long customerId;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Account> accounts;
//
//    // Constructor
//    public Customer(String username) {
//        if (username == null || username.isEmpty()) {
//            throw new IllegalArgumentException("Username cannot be null or empty");
//        }
//        this.username = username;
//    }
//
//    // Default constructor
//    public Customer() {}
//
//    public void addAccount(Account account) {
//        accounts.add(account);
//        account.setCustomer(this);
//    }
//
//    public void removeAccount(Account account) {
//        accounts.remove(account);
//        account.setCustomer(null);
//    }
//}
//

package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers", schema = "banking")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();  // Initialize list

    public Customer(String fullName) {
        this.fullName = fullName;
        this.username = fullName.toLowerCase().replaceAll(" ", "");
        this.accounts = new ArrayList<>();  // Initialize list in constructor
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }
}

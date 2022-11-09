package com.cognizant.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDetails {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int customerId;
    @Column(nullable = false)
    @Id
    String emailAddress;
    @Column(nullable = false)
    String password;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String phoneNumber;
    @Column
    String address;

}

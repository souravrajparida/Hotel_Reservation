package com.cognizant.authorization.repository;

import com.cognizant.authorization.model.UserRegistrationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginRepository extends JpaRepository<UserRegistrationDetails, String> {
    public UserRegistrationDetails findByEmailAddress(String emailAddress);
}

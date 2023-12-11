package com.mycompany.signupservice.repository;

import com.mycompany.signupservice.dto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {

    User save(User user);
    Optional<User> findByEmail(String email);

}

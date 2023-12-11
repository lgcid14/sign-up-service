package com.mycompany.signupservice.repository;

import com.mycompany.signupservice.dto.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    List<Phone> findByUserId(String userId);
}

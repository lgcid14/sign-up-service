package com.mycompany.signupservice.repository;

import com.mycompany.signupservice.dto.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void savePhones_ShouldSavePhonesInDatabase() {
        //Arrange
        Phone phone1 = new Phone();
        phone1.setNumber(87654321);
        phone1.setCityCode(370);
        phone1.setCountryCode("+569");
        phone1.setUserId("550e8400-e29b-41d4-a716-446655440000");
        Phone phone2 = new Phone();
        phone2.setNumber(87654321);
        phone2.setCityCode(370);
        phone2.setCountryCode("+569");
        phone2.setUserId("550e8400-e29b-41d4-a716-446655440000");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        //Act
        List<Phone> savedPhones = phoneRepository.saveAll(phones);
        //Assert
        assertTrue(savedPhones.size() == 2);
    }

    @Test
    public void findPhonesByUserId_ShouldFindPhonesInDatabase() {
        //Arrange
        String userId = "550e8400-e29b-41d4-a716-446655440000";
        Phone phone1 = new Phone();
        phone1.setNumber(87654321);
        phone1.setCityCode(370);
        phone1.setCountryCode("+569");
        phone1.setUserId(userId);
        Phone phone2 = new Phone();
        phone2.setNumber(87654322);
        phone2.setCityCode(370);
        phone2.setCountryCode("+569");
        phone2.setUserId(userId);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        //Act
        phoneRepository.saveAll(phones);
        List<Phone> foundPhones = phoneRepository.findByUserId(userId);
        //Assert
        assertTrue(foundPhones.size() == 2);
    }
}

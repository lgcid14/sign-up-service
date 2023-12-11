package com.mycompany.signupservice.repository;

import com.mycompany.signupservice.dto.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUser_ShouldSaveUserInDatabase() {
        //Arrange
        User user = new User();
        user.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        user.setName("Luis Cid");
        user.setEmail("luiscid@user.com");
        user.setPassword("a2asfGfdfdf4");
        user.setCreated(LocalDateTime.of(2023,11,30,12,30));
        user.setLastLogin(LocalDateTime.of(2023,11,30,12,32));
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0");
        user.setActive(true);
        //Act
        User savedUser = userRepository.save(user);
        //Assert
        assertNotNull(savedUser);
    }

    @Test
    public void findUserByEmail_ShouldNotFindUser() {
        //Arrange
        String nonExistentEmail = "non.existent.email@user.com";
        //Act
        Optional<User> notFoundUser = userRepository.findByEmail(nonExistentEmail);
        //Assert
        assertFalse(notFoundUser.isPresent());
    }

}

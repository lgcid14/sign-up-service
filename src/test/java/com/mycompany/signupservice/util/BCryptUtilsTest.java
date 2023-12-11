package com.mycompany.signupservice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BCryptUtilsTest {

    @Test
    public void encryptPassword() {
        BCryptUtils bCryptUtils = new BCryptUtils();
        String password = "a2asfGfdfdf4";
        String encryptPassword = bCryptUtils.encryptPassword(password);
        //Method isBlank introduced in Java 11
        assertFalse(encryptPassword.isBlank());
        assertNotNull(encryptPassword);
    }

}

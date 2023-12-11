package com.mycompany.signupservice.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailValidatorTest {

    @Test
    public void validateEmail() {
        EmailValidator emailValidator = new EmailValidator();
        String validEmail = "luiscid@user.com";
        String invalidEmail = "@user.com";
        assertTrue(emailValidator.isValidEmail(validEmail));
        assertFalse(emailValidator.isValidEmail(invalidEmail));

    }
}

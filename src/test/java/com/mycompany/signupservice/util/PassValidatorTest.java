package com.mycompany.signupservice.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PassValidatorTest {

    @Test
    public void validatePass() {
        PassValidator passValidator = new PassValidator();
        String validPass = "a2asfGfdfdf4";
        //Password with less than 8 characters
        String shortPass = "Ad2d2er";
        //Password with more than 12 characters
        String longPass = "a2asfGfdfdf4f";
        //Password with more than 2 digits
        String invalidPass1 = "a2asfGfdfd44";
        //Password with more than 2 uppercase
        String invalidPass2 = "A2asfGfdfdf4";
        //Password with special characters
        String invalidPass3 = "a2#sfGfdfdf4";
        assertTrue(passValidator.isValidPass(validPass));
        assertFalse(passValidator.isValidPass(shortPass));
        assertFalse(passValidator.isValidPass(longPass));
        assertFalse(passValidator.isValidPass(invalidPass1));
        assertFalse(passValidator.isValidPass(invalidPass2));
        assertFalse(passValidator.isValidPass(invalidPass3));
    }
}

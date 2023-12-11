package com.mycompany.signupservice.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void generateValidJwtToken() {
        String email = "luiscid@user.com";
        String password = "a2asfGfdfdf4";
        String jwtToken = jwtTokenProvider.generateAccessToken(email, password);
        assertNotNull(jwtToken);
    }

    @Test
    public void validateJwtToken() {
        String email = "luiscid@user.com";
        String password = "a2asfGfdfdf4";
        String jwtToken = jwtTokenProvider.generateValidAccessToken(email, password);
        assertNotNull(jwtToken);
    }

    @Test
    public void checkAuthentication() {
        String email = "luiscid@user.com";
        String password = "a2asfGfdfdf4";
        String jwtToken = jwtTokenProvider.generateValidAccessToken(email, password);
        Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
        assertNotNull(authentication);
    }

}

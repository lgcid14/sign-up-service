package com.mycompany.signupservice.controller;

import com.mycompany.signupservice.dto.request.LoginRequest;
import com.mycompany.signupservice.dto.request.SignupRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;
import com.mycompany.signupservice.service.impl.LoginServiceImpl;
import com.mycompany.signupservice.service.impl.SignupServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SignupControllerTest {

    @InjectMocks
    SignupController signupController;

    @Mock
    SignupServiceImpl signupService;

    @Mock
    LoginServiceImpl loginService;

    @Test
    public void signUpUser() {
        //Arrange
        SignupRequest request = mockSignupRequest();
        SignupResponse signupResponse = mockResponse();
        //Act
        when(signupService.signupUser(request)).thenReturn(signupResponse);
        ResponseEntity<SignupResponse> response = signupController.signup(request);
        //Assert
        assertNotNull(response);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    @Test
    public void loginUser() {
        //Arrange
        LoginRequest request = mockLoginRequest();
        SignupResponse signupResponse = mockResponse();
        //Act
        when(loginService.login(request)).thenReturn(signupResponse);
        ResponseEntity<SignupResponse> response = signupController.login(request);
        //Assert
        assertNotNull(response);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    private SignupRequest mockSignupRequest() {
        SignupRequest request = new SignupRequest();
        request.setName("Luis Cid");
        request.setEmail("luiscid@user.com");
        request.setPassword("a2asfGfdfdf4");
        return request;
    }

    private LoginRequest mockLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.setEmail("luiscid@user.com");
        request.setPassword("a2asfGfdfdf4");
        return request;
    }

    private SignupResponse mockResponse() {
        SignupResponse response = new SignupResponse();
        response.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        response.setName("Luis Cid");
        response.setEmail("luiscid@user.com");
        response.setPassword("a2asfGfdfdf4");
        response.setCreated("Dec 5, 2023 09:41:12 PM");
        response.setLastLogin("Dec 5, 2023 09:41:12 PM");
        response.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0");
        response.setActive(true);
        return response;
    }
}

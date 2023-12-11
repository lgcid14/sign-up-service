package com.mycompany.signupservice.service.impl;

import com.mycompany.signupservice.dto.UserPhone;
import com.mycompany.signupservice.dto.entity.Phone;
import com.mycompany.signupservice.dto.entity.User;
import com.mycompany.signupservice.dto.request.SignupRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;
import com.mycompany.signupservice.exception.GeneralServiceException;
import com.mycompany.signupservice.mapper.UserMapper;
import com.mycompany.signupservice.repository.PhoneRepository;
import com.mycompany.signupservice.repository.UserRepository;
import com.mycompany.signupservice.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SignupServiceImplTest {

    @InjectMocks
    private SignupServiceImpl signupService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PhoneRepository phoneRepository;

    @BeforeAll
    public void setUp(){
        userMapper = Mappers.getMapper(UserMapper.class);
        ReflectionTestUtils.setField(jwtTokenProvider, "SECRET_KEY",
                "jvXzECwyrAomZT8DtojdivBR1GMHm227ozBFimh+78HhFW/wLRsk/+dyPmR8UC34rVqn2lbchOvKfgGPAjvL3w==");
    }

    @Test
    public void signupUser_ShouldRegisterUserWithoutPhones() {
        //Arrange
        SignupRequest request = mockRequest();
        User user = mockUser();
        SignupResponse response = mockResponse();
        //Act
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userMapper.requestUserToUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(response);
        SignupResponse signupResponse = signupService.signupUser(request);
        //Assert
        assertNotNull(signupResponse);
    }

    @Test
    public void signupUser_ShouldRegisterUserWithPhones() {
        //Arrange
        SignupRequest request = mockRequest();
        request.setPhones(mockPhones());
        User user = mockUser();
        SignupResponse response = mockResponse();
        response.setPhones(mockPhones());
        Phone phone = new Phone();
        phone.setUserId(user.getId().toString());
        phone.setNumber(87654321);
        phone.setCityCode(370);
        phone.setCountryCode("+569");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        //Act
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userMapper.requestUserToUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(response);
        when(userMapper.mapUserPhonesToPhones(mockPhones())).thenReturn(phones);
        when(phoneRepository.saveAll(phones)).thenReturn(phones);
        when(userMapper.mapPhonesToUserPhones(phones)).thenReturn(mockPhones());
        SignupResponse signupResponse = signupService.signupUser(request);
        //Assert
        assertNotNull(signupResponse);
        assertNotNull(signupResponse.getPhones());
    }

    @Test
    public void signupUser_ShouldNotRegisterUserWithAlreadyRegisteredEmail() {
        //Arrange
        SignupRequest request = mockRequest();
        User user = mockUser();
        //Act
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        GeneralServiceException thrown = assertThrows(GeneralServiceException.class,
                () -> signupService.signupUser(request),
                "There is already a registered user with this email");
        //Assert
        assertTrue("There is already a registered user with this email".equals(thrown.getMessage()));
    }

    @Test
    public void signupUser_ShouldNotRegisterUserWithAnInvalidEmail() {
        //Arrange
        SignupRequest request = mockRequest();
        String invalidEmail = "@user.com";
        request.setEmail(invalidEmail);
        //Act
        GeneralServiceException thrown = assertThrows(GeneralServiceException.class,
                () -> signupService.signupUser(request),
                "Email format is invalid");
        //Assert
        assertTrue("Email format is invalid".equals(thrown.getMessage()));
    }

    @Test
    public void signupUser_ShouldNotRegisterUserWithAnInvalidPassword() {
        //Arrange
        SignupRequest request = mockRequest();
        String invalidPassword = "1234";
        request.setPassword(invalidPassword);
        //Act
        GeneralServiceException thrown = assertThrows(GeneralServiceException.class,
                () -> signupService.signupUser(request),
                "Password format is invalid");
        //Assert
        assertTrue("Password format is invalid".equals(thrown.getMessage()));
    }

    private User mockUser() {
        User user = new User();
        user.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        user.setName("Luis Cid");
        user.setEmail("luiscid@user.com");
        user.setPassword("a2asfGfdfdf4");
        user.setCreated(LocalDateTime.of(2023,11,30,12,30));
        user.setLastLogin(LocalDateTime.of(2023,11,30,12,32));
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0");
        user.setActive(true);
        return user;
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

    private SignupRequest mockRequest() {
        SignupRequest request = new SignupRequest();
        request.setName("Luis Cid");
        request.setEmail("luiscid@user.com");
        request.setPassword("a2asfGfdfdf4");
        return request;
    }

    private List<UserPhone> mockPhones() {
        UserPhone phone = new UserPhone();
        phone.setNumber(87654321);
        phone.setCityCode(370);
        phone.setCountryCode("+569");
        List<UserPhone> phones = new ArrayList<>();
        phones.add(phone);
        return phones;
    }

}

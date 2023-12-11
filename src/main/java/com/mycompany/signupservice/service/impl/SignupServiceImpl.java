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
import com.mycompany.signupservice.service.SignupService;
import com.mycompany.signupservice.util.BCryptUtils;
import com.mycompany.signupservice.util.EmailValidator;
import com.mycompany.signupservice.util.PassValidator;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SignupServiceImpl implements SignupService {

    private UserRepository userRepository;

    private PhoneRepository phoneRepository;

    private JwtTokenProvider jwtTokenProvider;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private static final Logger logger = LoggerFactory.getLogger(SignupServiceImpl.class);

    @Autowired
    public SignupServiceImpl(final UserRepository userRepository,
                             final PhoneRepository phoneRepository,
                             final JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignupResponse signupUser(SignupRequest request) {
        SignupResponse response = new SignupResponse();
        try {
            //validate email
            if (!EmailValidator.isValidEmail(request.getEmail())) {
                throw new GeneralServiceException("Email format is invalid", 10);
            }
            //validate password
            if (!PassValidator.isValidPass(request.getPassword())) {
                throw new GeneralServiceException("Password format is invalid", 20);
            }
            //check if user email is already registered
            Optional<User> user = this.userRepository.findByEmail(request.getEmail());
            //save user only if there is no registered user with that email
            if (!user.isPresent()) {
                User userResponse = userMapper.requestUserToUser(request);
                String jwtToken = this.jwtTokenProvider.generateValidAccessToken(request.getEmail(),
                        request.getPassword());
                if (jwtToken != null) {
                    userResponse.setToken(jwtToken);
                }
                userResponse.setPassword(BCryptUtils.encryptPassword(request.getPassword()));
                userResponse = this.userRepository.save(userResponse);
                final UUID userId = userResponse.getId();
                response = userMapper.userToUserResponse(userResponse);
                List<Phone> phones = new ArrayList<>();
                //if user has phones, they are saved
                if (request.getPhones() != null && !request.getPhones().isEmpty()) {
                    phones = userMapper.mapUserPhonesToPhones(request.getPhones());
                    phones.stream().forEach(phone -> phone.setUserId(String.valueOf(userId)));
                    //Method not for interface Predicate introduced in Java 11
                    //Local-Variable syntax for Lambda introduced in Java 11
                    phones = phones.stream().filter(Predicate.not((var phone) -> phone == null)).collect(Collectors.toList());
                    this.phoneRepository.saveAll(phones);
                }
                if (!phones.isEmpty()) {
                    List<UserPhone> userPhones = userMapper.mapPhonesToUserPhones(phones);
                    response.setPhones(userPhones);
                }
            } else {
                throw new GeneralServiceException("There is already a registered user with this email", 30);
            }
        } catch (Exception e) {
            handleServiceExceptions(e);
        }
        return response;
    }

    private GeneralServiceException handleServiceExceptions (Exception e) {
        String errorMessage = e.getMessage();
        int errorCode;

        switch (errorMessage) {
            case "Email format is invalid":
                logger.error("Email format is invalid");
                errorCode = -10;
                break;

            case "Password format is invalid":
                logger.error("Password format is invalid");
                errorCode = -20;
                break;

            case "There is already a registered user with this email":
                logger.error("There is already a registered user with this email");
                errorCode = -30;
                break;

            default:
                logger.error("An exception was produced: " + errorMessage);
                throw new GeneralServiceException("Unknown error message: " + errorMessage, -1);
        }
        throw new GeneralServiceException(errorMessage, errorCode);
    }

}

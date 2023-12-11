package com.mycompany.signupservice.service.impl;

import com.mycompany.signupservice.dto.UserPhone;
import com.mycompany.signupservice.dto.entity.Phone;
import com.mycompany.signupservice.dto.entity.User;
import com.mycompany.signupservice.dto.request.LoginRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;
import com.mycompany.signupservice.exception.GeneralServiceException;
import com.mycompany.signupservice.mapper.UserMapper;
import com.mycompany.signupservice.repository.PhoneRepository;
import com.mycompany.signupservice.repository.UserRepository;
import com.mycompany.signupservice.security.JwtTokenProvider;
import com.mycompany.signupservice.service.LoginService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    private PhoneRepository phoneRepository;

    private JwtTokenProvider jwtTokenProvider;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, final PhoneRepository phoneRepository,
                            final JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignupResponse login(LoginRequest request) {
        SignupResponse response;
        try {
            Optional<User> user = this.userRepository.findByEmail(request.getEmail());
            if (user.isPresent()) {
                response = userMapper.userToUserResponse(user.get());
                List<Phone> phones = this.phoneRepository.findByUserId(user.get().getId().toString());
                List<UserPhone> userPhones = userMapper.mapPhonesToUserPhones(phones);
                String jwtToken = this.jwtTokenProvider.generateValidAccessToken(user.get().getEmail(),
                        user.get().getPassword());
                //update last login
                response.setLastLogin(DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss a")
                        .format(LocalDateTime.now()));
                response.setToken(jwtToken);
                response.setPhones(userPhones);
            } else {
                throw new GeneralServiceException("User not found", 40);
            }
        } catch (Exception e) {
            String message = "";
            int errorCode;
            if ("User not found".equals(e.getMessage())) {
                message = "User not found";
                errorCode = -40;
            } else {
                message = e.getMessage();
                errorCode = -1;
            }
            throw new GeneralServiceException(message, errorCode);
        }
        return response;
    }
}

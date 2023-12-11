package com.mycompany.signupservice.service;

import com.mycompany.signupservice.dto.request.LoginRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;

public interface LoginService {

    SignupResponse login(LoginRequest request);
}

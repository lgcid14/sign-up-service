package com.mycompany.signupservice.service;

import com.mycompany.signupservice.dto.request.SignupRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;

public interface SignupService {

    SignupResponse signupUser(SignupRequest request);
}

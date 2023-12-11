package com.mycompany.signupservice.controller;

import com.mycompany.signupservice.dto.request.LoginRequest;
import com.mycompany.signupservice.dto.request.SignupRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;
import com.mycompany.signupservice.service.LoginService;
import com.mycompany.signupservice.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup-service")
public class SignupController {

    private SignupService signupService;

    private LoginService loginService;

    @Autowired
    public SignupController(final SignupService signupService, final LoginService loginService) {
        this.signupService = signupService;
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(this.signupService.signupUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<SignupResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(this.loginService.login(request));
    }

}

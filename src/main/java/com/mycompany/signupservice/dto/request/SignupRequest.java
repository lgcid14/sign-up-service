package com.mycompany.signupservice.dto.request;

import com.mycompany.signupservice.dto.UserPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String name;
    private String email;
    private String password;
    private List<UserPhone> phones;
}

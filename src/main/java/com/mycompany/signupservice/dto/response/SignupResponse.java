package com.mycompany.signupservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.signupservice.dto.UserPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("created")
    private String created;
    @JsonProperty("lastlogin")
    private String lastLogin;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isActive")
    private boolean active;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phones")
    private List<UserPhone> phones;
}

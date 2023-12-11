package com.mycompany.signupservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPhone {

    private long number;
    private int cityCode;
    private String countryCode;
}

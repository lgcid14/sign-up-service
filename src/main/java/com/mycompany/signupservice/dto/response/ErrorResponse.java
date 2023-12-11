package com.mycompany.signupservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String timestamp;
    private int codigo;
    private String detail;
}

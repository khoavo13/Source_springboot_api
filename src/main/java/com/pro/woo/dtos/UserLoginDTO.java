package com.pro.woo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank(message = "Phone number cannot be blank")
    @JsonProperty("phone_number")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}

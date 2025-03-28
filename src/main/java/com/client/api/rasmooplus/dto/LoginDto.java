package com.client.api.rasmooplus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String username;

    private String password;

    private String refreshToken;

    @NotBlank
    private String clientId;

    @NotBlank
    private String clientSecret;

    @NotBlank
    private String grantType;

}

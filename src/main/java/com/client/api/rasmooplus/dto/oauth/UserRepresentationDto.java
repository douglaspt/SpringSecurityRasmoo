package com.client.api.rasmooplus.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRepresentationDto {

    private boolean enabled;

    private String username;

    private boolean emailVerified;

    private String email;

    private String firstName;

    private String lastName;

    private List<String> groups;

    private List<CredentialRepresentationDto> credentials;

    @Data
    @Builder
    public static class CredentialRepresentationDto {
        private String type;
        private String value;
        private boolean temporary;
    }

}

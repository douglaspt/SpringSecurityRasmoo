package com.client.api.rasmooplus.controller;

import com.client.api.rasmooplus.dto.LoginDto;
import com.client.api.rasmooplus.dto.TokenDto;
import com.client.api.rasmooplus.dto.UserDetailsDto;
import com.client.api.rasmooplus.model.redis.UserRecoveryCode;
import com.client.api.rasmooplus.service.AuthenticationService;
import com.client.api.rasmooplus.service.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


//@Api(tags = SwaggerConfig.AUTENTICACAO)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> auth(@RequestBody @Valid LoginDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.auth(dto));
    }


    @PostMapping(value = "/recovery-code/send",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyAuthority('CLIENT_READ_WRITE', ADMIN_READ, ADMIN_WRITE)")
    public ResponseEntity<?> sendRecoveryCode(@RequestBody @Valid UserRecoveryCode dto) {
        userDetailsService.sendRecoveryCode(dto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping(value = "/recovery-code/")
    @PreAuthorize(value = "hasAnyAuthority('CLIENT_READ_WRITE', ADMIN_READ, ADMIN_WRITE)")
    public ResponseEntity<?> recoveryCodeIsValid(@RequestParam("recoveryCode") String recoveryCode,
                                                 @RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body( userDetailsService.recoveryCodeIsValid(recoveryCode, email));
    }

    @PatchMapping(value = "/recovery-code/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyAuthority('CLIENT_READ_WRITE', ADMIN_READ, ADMIN_WRITE)")
    public ResponseEntity<?> updatePasswordByRecoveryCode(@RequestBody @Valid UserDetailsDto dto) {
        userDetailsService.updatePasswordByRecoveryCode(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

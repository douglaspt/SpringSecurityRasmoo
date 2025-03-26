package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.LoginDto;
import com.client.api.rasmooplus.dto.TokenDto;

public interface AuthenticationService {

    String auth(LoginDto dto);

}

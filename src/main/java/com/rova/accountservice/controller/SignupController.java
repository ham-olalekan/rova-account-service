package com.rova.accountservice.controller;

import com.rova.accountservice.dals.IUserDetails;
import com.rova.accountservice.dto.CreateUserDto;
import com.rova.accountservice.dto.TokensDto;
import com.rova.accountservice.dto.UserDto;
import com.rova.accountservice.exceptions.CommonsException;
import com.rova.accountservice.services.impl.UserService;
import com.rova.accountservice.services.impl.UserServiceImpl;
import com.rova.accountservice.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.rova.accountservice.util.Constants.X_ACCESS_TOKEN;
import static com.rova.accountservice.util.Constants.X_REFRESH_TOKEN;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SignupController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/v1/user/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateUserDto signupDto, HttpServletResponse httpServletResponse) throws CommonsException {
        UserDto userDto = userService.registerNewUser(signupDto);

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), signupDto.getPassword()));
        TokensDto tokensDto = jwtTokenUtils.generateTokens((IUserDetails) authenticate.getPrincipal());
        httpServletResponse.addHeader(X_ACCESS_TOKEN, tokensDto.getAccessToken());
        httpServletResponse.addHeader(X_REFRESH_TOKEN, tokensDto.getRefreshToken());
    }
}

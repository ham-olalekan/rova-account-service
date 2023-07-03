package com.rova.accountservice.controller;

import com.rova.accountservice.dals.IUserDetails;
import com.rova.accountservice.dto.FUserDto;
import com.rova.accountservice.dto.ResponseDto;
import com.rova.accountservice.exceptions.CommonsException;
import com.rova.accountservice.exceptions.UserNotFoundException;
import com.rova.accountservice.services.impl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rova.accountservice.enums.Authorities.USER_PREAUTHORIZE;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize(USER_PREAUTHORIZE)
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation(
            value = "Get User",
            notes = "Retrieve the user information",
            authorizations = {
                    @Authorization(
                            value = "Bearer Token",
                            scopes = {
                                    @AuthorizationScope(scope = "read:user", description = "Read user information")
                            }
                    )
            }
    )
    public ResponseDto<?> getUser(Authentication authentication) throws CommonsException {
        long userId = IUserDetails.getId(authentication);
        FUserDto user = userService.findByUserId(userId);
        return ResponseDto.wrapSuccessResult(user, "request successful");
    }
}

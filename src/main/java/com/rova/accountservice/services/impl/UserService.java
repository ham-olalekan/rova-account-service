package com.rova.accountservice.services.impl;

import com.rova.accountservice.dto.CreateUserDto;
import com.rova.accountservice.dto.FUserDto;
import com.rova.accountservice.dto.UserDto;
import com.rova.accountservice.exceptions.CommonsException;

public interface UserService {
    UserDto registerNewUser(CreateUserDto signupDto);

    FUserDto findByUserId(long userId) throws CommonsException;
}

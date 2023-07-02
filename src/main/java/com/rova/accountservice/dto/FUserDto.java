package com.rova.accountservice.dto;

import com.rova.accountservice.dals.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class FUserDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String username;
    private String countryCode;
    private String phoneNo;
    private String imageUrl;


    public static FUserDto toDTO(User user) {
        FUserDto FUserDto = new FUserDto();
        BeanUtils.copyProperties(user, FUserDto);
        return FUserDto;
    }
}

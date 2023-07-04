package com.rova.accountservice.dto;

import com.rova.accountservice.annotations.DoesNotExist;
import com.rova.accountservice.annotations.ValidEmailAddress;
import com.rova.accountservice.annotations.ValidPhoneNumber;
import com.rova.accountservice.annotations.ValidCountryCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.rova.accountservice.util.Constants.EntityColumns.EMAIL;
import static com.rova.accountservice.util.Constants.EntityColumns.PHONE_NO;
import static com.rova.accountservice.util.Constants.EntityNames.USERS;

@Getter
@Setter
@Valid
public class CreateUserDto implements ICreateUserDto {
    @Size(max = 20, min = 2, message = "firstname.accepted_length")
    @NotBlank(message = "firstname.not_blank")
    private String firstName;

    @Size(max = 20, min = 2, message = "lastname.accepted_length")
    @NotBlank(message = "lastname.not_blank")
    private String lastName;

    @ValidPhoneNumber
    @NotBlank(message = "phone_number.not_blank")
    @DoesNotExist(table = USERS, columnName = PHONE_NO, message = "user phone_no exists")
    private String phoneNo;

    @ValidEmailAddress
    @NotBlank(message = "email not blank")
    @DoesNotExist(table = USERS, columnName = EMAIL, message = "user email exists")
    private String email;

    @ValidCountryCode
    @NotBlank(message = "country_code.not_blank")
    private String countryCode;

    private String referralCode;

    private String source = "MOBILE_APP";
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    public String getSource() {
        if (StringUtils.hasText(source)) return source;
        return "MOBILE_APP";
    }
}

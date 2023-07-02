package com.rova.accountservice.annotations.validators;


import com.rova.accountservice.annotations.ValidCountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void initialize(ValidCountryCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    @Cacheable(value = "countryCodeExist", key = "#value", condition = "#result == true")
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String query = "SELECT EXISTS(SELECT id FROM currencies WHERE UPPER(country_code) = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, Boolean.class, value));
    }
}

package com.rova.accountservice.config;

import com.rova.accountservice.config.web.AntMatchersEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.Stack;

@Configuration
@RequiredArgsConstructor
public class CustomAntMatchersEndpoint {

    private final AntMatchersEndpoints antMatchersEndpoints;

    @Bean
    @Primary
    public AntMatchersEndpoints antMatchersEndpoints() {
        Stack<AntMatchersEndpoints.AntMatchersEndpoint> antMatchersEndpointSet
                = antMatchersEndpoints.getAntMatchersEndpoints();
        antMatchersEndpointSet.add(new AntMatchersEndpoints
                .AntMatchersEndpoint(POST, "/api/v1/user/signup"));
        antMatchersEndpointSet.add(new AntMatchersEndpoints
                .AntMatchersEndpoint(GET, "/swagger-ui/index.html"));
        return antMatchersEndpoints;
    }

}

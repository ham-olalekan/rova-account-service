package com.rova.accountservice.components;

import com.rova.accountservice.config.web.AntMatchersEndpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Stack;

import static com.rova.accountservice.enums.Authorities.API_USER;
import static com.rova.accountservice.enums.Authorities.USER;

@Component
public class DefaultAntMatchersEndpoint {

    @Bean("defaultAntMatchersEndpoints")
    public AntMatchersEndpoints antMatchersEndpoints() {
        AntMatchersEndpoints antMatchersEndpoints = new AntMatchersEndpoints();
        Stack<AntMatchersEndpoints.AntMatchersEndpoint> antMatchersEndpointSet = new Stack<>();
        antMatchersEndpointSet.add(new AntMatchersEndpoints.AntMatchersEndpoint(HttpMethod.GET, "/"));
        antMatchersEndpointSet.add(new AntMatchersEndpoints.AntMatchersEndpoint(HttpMethod.GET, "/swagger-ui/index.html/**"));
        antMatchersEndpointSet.add(new AntMatchersEndpoints.AntMatchersEndpoint("/api/**", USER.name()));
        antMatchersEndpointSet.add(new AntMatchersEndpoints.AntMatchersEndpoint("/api/v1/user/signup/**", API_USER.name()));
        antMatchersEndpointSet.add(new AntMatchersEndpoints.AntMatchersEndpoint("/api/v1/user/**", USER.name()));

        AntMatchersEndpoints.AntMatchersEndpoint antMatchersEndpoint =
                new AntMatchersEndpoints.AntMatchersEndpoint("/actuator/**");
        antMatchersEndpointSet.add(antMatchersEndpoint);
        antMatchersEndpoint.setPermitAll(true);

        antMatchersEndpoints.setAntMatchersEndpoints(antMatchersEndpointSet);
        return antMatchersEndpoints;
    }
}

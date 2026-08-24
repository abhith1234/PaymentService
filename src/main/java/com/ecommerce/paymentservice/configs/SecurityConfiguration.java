package com.ecommerce.paymentservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        // @formatter:off
        httpSecurity
                .cors(
                        cors -> cors.configurationSource(request -> {
                            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                            corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:4101", "http://localhost:4200"));
                            corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            corsConfig.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type"));
                            return corsConfig;
                        })
                )
                .csrf(csrf -> csrf.disable()) //In case you deploy service on cloud, you might not need this line
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                ;
        return httpSecurity.build();
    }
}


package com.test.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(auth -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/.well-known/acme-challenge/**").permitAll()  // Certbot을 위한 예외
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}

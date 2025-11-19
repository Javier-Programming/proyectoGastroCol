package com.appGastroCol.product_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Permitir todas las solicitudes sin autenticación
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login.disable()); // Desactivar login automático

        return http.build();
    }
}

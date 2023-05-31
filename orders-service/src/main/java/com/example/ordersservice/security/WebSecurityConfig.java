package com.example.ordersservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/","/swagger-ui/**", "/swagger-ui/index.html",  "/v3/api-docs/**").permitAll()
                        .requestMatchers("/order/", "/order/all", "/order/all/**", "/order/{id}").hasRole("USER")
                        .requestMatchers("/order/add/**").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults())
                .csrf().disable();
        ;
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$CmynNnwTzLU4QQ8XslbY8O0TlT8nIrrss3yGR0JUeTPXC7UPLNXZe")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$10$CmynNnwTzLU4QQ8XslbY8O0TlT8nIrrss3yGR0JUeTPXC7UPLNXZe").roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

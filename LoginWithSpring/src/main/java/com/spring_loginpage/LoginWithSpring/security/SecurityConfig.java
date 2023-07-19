package com.spring_loginpage.LoginWithSpring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails employee = User.builder()
                .username("arun")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails manager = User.builder()
                .username("sreekanth")
                .password("{noop}test456")
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(employee,manager);
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer->configurer
                        .anyRequest().authenticated())
                        .formLogin(form->form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }
}










package com.example.expensify.security;

import com.example.expensify.entity.ExpensifyUser;
import com.example.expensify.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
  @Autowired ObjectMapper objectMapper;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain web(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults())
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("/employees")
                    .hasAuthority(Role.ROLE_REVIEWER.toString())
                    .requestMatchers("/expenses/{expenseId}/status")
                    .hasAuthority(Role.ROLE_REVIEWER.toString())
                    .requestMatchers("/employees/{employeeId}/expenses/**")
                    .hasAuthority(Role.ROLE_EMPLOYEE.toString())
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults())
        .formLogin(
            form ->
                form.successHandler(
                        (request, response, authentication) -> {
                          response.setStatus(HttpServletResponse.SC_OK);
                          response.setContentType("application/json");

                          ExpensifyUser expensifyUser =
                              (ExpensifyUser) authentication.getPrincipal();
                          response
                              .getWriter()
                              .write(
                                  objectMapper
                                      .writerFor(ExpensifyUser.class)
                                      .writeValueAsString(expensifyUser));
                        })
                    .failureHandler(
                        (request, response, exception) -> {
                          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }));
    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

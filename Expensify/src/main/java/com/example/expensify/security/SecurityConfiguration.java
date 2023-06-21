package com.example.expensify.security;

import com.example.expensify.entity.Role;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain web(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
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
                          response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        })
                    .failureHandler(
                        (request, response, exception) -> {
                          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }));
    return http.build();
  }
}

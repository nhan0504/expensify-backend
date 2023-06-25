package com.example.expensify.security;

import com.example.expensify.entity.Role;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< Updated upstream
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
=======
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;
import java.util.Arrays;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    http.csrf(csrf -> csrf.disable())
=======
    http.cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
                          response.setContentType("application/json");

                          UserDetails principal = (UserDetails) authentication.getPrincipal();
                          String principalDetails =
                              String.format(
                                  "{\"username\": \"%s\", \"authorities\": \"%s\"}",
                                    principal.getUsername(),
                                    principal.getAuthorities().toArray()[0].toString());

                          PrintWriter out = response.getWriter();
                          out.print(principalDetails);
                          out.flush();
>>>>>>> Stashed changes
                        })
                    .failureHandler(
                        (request, response, exception) -> {
                          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }));
    return http.build();
  }
<<<<<<< Updated upstream
=======

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
>>>>>>> Stashed changes
}

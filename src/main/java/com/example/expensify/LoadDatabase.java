package com.example.expensify;

import com.example.expensify.entity.*;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ReviewerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(
      EmployeeRepository employeeRepository,
      ReviewerRepository reviewerRepository,
      PasswordEncoder passwordEncoder) {

    return args -> {
      Status status = Status.builder().state(State.IN_REVIEW).build();
      Expense expense =
          Expense.builder()
              .merchant("merchant_1")
              .amount(123.45)
              .description("description_1")
              .purchaseDate(LocalDate.parse("2023-06-06"))
              .status(status)
              .build();
      Employee employee1 =
          new Employee("employee1", passwordEncoder.encode("password"), Role.ROLE_EMPLOYEE);
      employee1.getExpenses().add(expense);
      employeeRepository.save(employee1);
      employeeRepository.save(
          new Employee("employee2", passwordEncoder.encode("password"), Role.ROLE_EMPLOYEE));
      employeeRepository.save(
          new Employee("employee3", passwordEncoder.encode("password"), Role.ROLE_EMPLOYEE));
      reviewerRepository.save(
          new Reviewer("reviewer1", passwordEncoder.encode("password"), Role.ROLE_REVIEWER));
      reviewerRepository.save(
          new Reviewer("reviewer2", passwordEncoder.encode("password"), Role.ROLE_REVIEWER));
    };
  }
}

package com.example.expensify;

import com.example.expensify.entity.*;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ReviewerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(
      EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository) {

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
      Employee employee1 = new Employee("employee1", "password", Role.ROLE_EMPLOYEE);
      employee1.getExpenses().add(expense);
      employeeRepository.save(employee1);
      employeeRepository.save(new Employee("employee2", "password", Role.ROLE_EMPLOYEE));
      employeeRepository.save(new Employee("employee3", "password", Role.ROLE_EMPLOYEE));
      reviewerRepository.save(new Reviewer("reviewer1", "password", Role.ROLE_REVIEWER));
      reviewerRepository.save(new Reviewer("reviewer2", "password", Role.ROLE_REVIEWER));
    };
  }
}

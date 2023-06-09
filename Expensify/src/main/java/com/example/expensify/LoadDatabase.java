package com.example.expensify;

import com.example.expensify.entity.*;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import com.example.expensify.repository.ReviewerRepository;
import com.example.expensify.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
      EmployeeRepository employeeRepository,
      ReviewerRepository reviewerRepository,
      ExpenseRepository expenseRepository,
      StatusRepository statusRepository) {

    return args -> {
      Status status = statusRepository.save(Status.builder().state(State.IN_REVIEW).build());
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
      expenseRepository.save(expense);
      employeeRepository.save(new Employee("employee2", "password", Role.ROLE_EMPLOYEE));
      employeeRepository.save(new Employee("employee3", "password", Role.ROLE_EMPLOYEE));
      reviewerRepository.save(new Reviewer("reviewer1", "password", Role.ROLE_REVIEWER));
      reviewerRepository.save(new Reviewer("reviewer2", "password", Role.ROLE_REVIEWER));
    };
  }
}

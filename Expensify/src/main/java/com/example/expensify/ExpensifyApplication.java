package com.example.expensify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ExpensifyApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpensifyApplication.class, args);
  }
}

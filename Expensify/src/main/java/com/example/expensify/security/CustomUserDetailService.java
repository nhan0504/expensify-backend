package com.example.expensify.security;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Reviewer;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ReviewerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;
  private final ReviewerRepository reviewerRepository;

  public CustomUserDetailService(EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository) {
    this.employeeRepository = employeeRepository;
    this.reviewerRepository = reviewerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Employee> employee = employeeRepository.findByUsername(username);
    if (employee.isPresent()) return employee.get();
    Optional<Reviewer> reviewer = reviewerRepository.findByUsername(username);
    if (reviewer.isPresent()) return reviewer.get();

    throw new UsernameNotFoundException("User not found");
  }
}

package com.example.expensify.repository;

import com.example.expensify.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
  Optional<Reviewer> findByUsername(String username);
}

package com.example.expensify.repository;

import com.example.expensify.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {}

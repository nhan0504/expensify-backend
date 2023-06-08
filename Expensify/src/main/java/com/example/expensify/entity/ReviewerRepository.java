package com.example.expensify.entity;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReviewerRepository extends JpaRepository<Reviewer, Long> {}

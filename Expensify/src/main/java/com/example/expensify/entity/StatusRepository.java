package com.example.expensify.entity;

import org.springframework.data.jpa.repository.JpaRepository;

interface StatusRepository extends JpaRepository<Status, Long> {}

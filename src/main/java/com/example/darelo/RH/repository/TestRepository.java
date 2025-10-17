package com.example.darelo.RH.repository;

import com.example.darelo.RH.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}

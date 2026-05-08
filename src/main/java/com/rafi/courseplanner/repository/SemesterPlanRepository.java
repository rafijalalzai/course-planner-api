package com.rafi.courseplanner.repository;

import com.rafi.courseplanner.model.SemesterPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterPlanRepository extends JpaRepository<SemesterPlan, Long> {
}

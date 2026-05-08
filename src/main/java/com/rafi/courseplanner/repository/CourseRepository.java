package com.rafi.courseplanner.repository;

import com.rafi.courseplanner.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}

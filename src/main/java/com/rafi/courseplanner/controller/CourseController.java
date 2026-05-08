package com.rafi.courseplanner.controller;

import com.rafi.courseplanner.dto.CourseRequest;
import com.rafi.courseplanner.dto.CourseResponse;
import com.rafi.courseplanner.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponse addCourse(@RequestBody CourseRequest request) {
        return courseService.addCourse(request);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{code}")
    public CourseResponse getCourseByCode(@PathVariable String code) {
        return courseService.getCourseByCode(code);
    }

    @DeleteMapping("/{code}")
    public String deleteCourse(@PathVariable String code) {
        courseService.deleteCourse(code);
        return "Course deleted successfully: " + code.toUpperCase();
    }

    @PostMapping("/eligible")
    public List<CourseResponse> getEligibleCourses(@RequestBody List<String> completedCourseCodes) {
        return courseService.getEligibleCourses(completedCourseCodes);
    }

}

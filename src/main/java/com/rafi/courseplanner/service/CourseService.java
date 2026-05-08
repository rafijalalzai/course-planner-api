package com.rafi.courseplanner.service;

import com.rafi.courseplanner.dto.CourseRequest;
import com.rafi.courseplanner.dto.CourseResponse;
import com.rafi.courseplanner.model.Course;
import com.rafi.courseplanner.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * CourseService handles course logic:
 * adds a course
 * checks if course input is valid
 * converts course codes to upperCase
 * gets all courses
 * gets one course by code
 * deletes a course
 * checks which courses a student is eligible for (preRequesite)
 */

@Service
public class CourseService {
	
	private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponse addCourse(CourseRequest request) {
        validateCourseRequest(request);

        String code = request.getCode().trim().toUpperCase();
        String title = request.getTitle().trim();
        int credits = request.getCredits();

        List<String> prerequisites = cleanCourseCodes(request.getPrerequisites());

        Course course = new Course(code, title, credits, prerequisites);
        Course savedCourse = courseRepository.save(course);

        return convertToResponse(savedCourse);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> responses = new ArrayList<CourseResponse>();

        for (int i = 0; i < courses.size(); i++) {
            responses.add(convertToResponse(courses.get(i)));
        }

        return responses;
    }

    public CourseResponse getCourseByCode(String code) {
        String cleanCode = code.trim().toUpperCase();

        Optional<Course> courseOptional = courseRepository.findById(cleanCode);

        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found: " + cleanCode);
        }

        return convertToResponse(courseOptional.get());
    }

    public void deleteCourse(String code) {
        String cleanCode = code.trim().toUpperCase();

        if (!courseRepository.existsById(cleanCode)) {
            throw new RuntimeException("Course not found: " + cleanCode);
        }

        courseRepository.deleteById(cleanCode);
    }

    public List<CourseResponse> getEligibleCourses(List<String> completedCourseCodes) {
        List<String> completed = cleanCourseCodes(completedCourseCodes);

        List<Course> allCourses = courseRepository.findAll();
        List<CourseResponse> eligibleCourses = new ArrayList<CourseResponse>();

        for (int i = 0; i < allCourses.size(); i++) {
            Course course = allCourses.get(i);

            boolean alreadyCompleted = completed.contains(course.getCode());
            boolean prerequisitesCompleted = completed.containsAll(course.getPrerequisites());

            if (!alreadyCompleted && prerequisitesCompleted) {
                eligibleCourses.add(convertToResponse(course));
            }
        }

        return eligibleCourses;
    }

    private void validateCourseRequest(CourseRequest request) {
        if (request.getCode() == null || request.getCode().trim().length() == 0) {
            throw new RuntimeException("Course code cannot be empty.");
        }

        if (request.getTitle() == null || request.getTitle().trim().length() == 0) {
            throw new RuntimeException("Course title cannot be empty.");
        }

        if (request.getCredits() <= 0) {
            throw new RuntimeException("Credits must be greater than 0.");
        }
    }

    private List<String> cleanCourseCodes(List<String> codes) {
        List<String> cleaned = new ArrayList<String>();

        if (codes == null) {
            return cleaned;
        }

        for (int i = 0; i < codes.size(); i++) {
            String code = codes.get(i);

            if (code != null && code.trim().length() > 0) {
                cleaned.add(code.trim().toUpperCase());
            }
        }

        return cleaned;
    }

    private CourseResponse convertToResponse(Course course) {
        return new CourseResponse(
                course.getCode(),
                course.getTitle(),
                course.getCredits(),
                course.getPrerequisites()
        );
    }
}

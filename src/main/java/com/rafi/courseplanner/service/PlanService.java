package com.rafi.courseplanner.service;

import com.rafi.courseplanner.dto.PlanRequest;
import com.rafi.courseplanner.dto.PlanResponse;
import com.rafi.courseplanner.model.Course;
import com.rafi.courseplanner.model.SemesterPlan;
import com.rafi.courseplanner.repository.CourseRepository;
import com.rafi.courseplanner.repository.SemesterPlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PlanService handles semester planning logic:
 * creates a semester plan
 * checks if courses exist before adding them to a plan
 * calculates total credits
 * finds missing prerequisites
 * gets all semester plans
 * gets one plan by ID
 * deletes a plan
 */

@Service
public class PlanService {
	private SemesterPlanRepository semesterPlanRepository;
    private CourseRepository courseRepository;

    public PlanService(SemesterPlanRepository semesterPlanRepository,
                       CourseRepository courseRepository) {
        this.semesterPlanRepository = semesterPlanRepository;
        this.courseRepository = courseRepository;
    }

    public PlanResponse createPlan(PlanRequest request) {
        validatePlanRequest(request);

        String semesterName = request.getSemesterName().trim();
        List<String> courseCodes = cleanCourseCodes(request.getCourseCodes());

        for (int i = 0; i < courseCodes.size(); i++) {
            String code = courseCodes.get(i);

            if (!courseRepository.existsById(code)) {
                throw new RuntimeException("Cannot add unknown course to plan: " + code);
            }
        }

        SemesterPlan plan = new SemesterPlan(semesterName, courseCodes);
        SemesterPlan savedPlan = semesterPlanRepository.save(plan);

        int totalCredits = calculateTotalCredits(courseCodes);
        List<String> missingPrerequisites = findMissingPrerequisites(courseCodes);

        return new PlanResponse(
                savedPlan.getId(),
                savedPlan.getSemesterName(),
                savedPlan.getCourseCodes(),
                totalCredits,
                missingPrerequisites
        );
    }

    public List<PlanResponse> getAllPlans() {
        List<SemesterPlan> plans = semesterPlanRepository.findAll();
        List<PlanResponse> responses = new ArrayList<PlanResponse>();

        for (int i = 0; i < plans.size(); i++) {
            SemesterPlan plan = plans.get(i);

            int totalCredits = calculateTotalCredits(plan.getCourseCodes());
            List<String> missingPrerequisites = findMissingPrerequisites(plan.getCourseCodes());

            responses.add(new PlanResponse(
                    plan.getId(),
                    plan.getSemesterName(),
                    plan.getCourseCodes(),
                    totalCredits,
                    missingPrerequisites
            ));
        }

        return responses;
    }

    public PlanResponse getPlanById(Long id) {
        Optional<SemesterPlan> optionalPlan = semesterPlanRepository.findById(id);

        if (optionalPlan.isEmpty()) {
            throw new RuntimeException("Semester plan not found with ID: " + id);
        }

        SemesterPlan plan = optionalPlan.get();

        int totalCredits = calculateTotalCredits(plan.getCourseCodes());
        List<String> missingPrerequisites = findMissingPrerequisites(plan.getCourseCodes());

        return new PlanResponse(
                plan.getId(),
                plan.getSemesterName(),
                plan.getCourseCodes(),
                totalCredits,
                missingPrerequisites
        );
    }

    public void deletePlan(Long id) {
        if (!semesterPlanRepository.existsById(id)) {
            throw new RuntimeException("Semester plan not found with ID: " + id);
        }

        semesterPlanRepository.deleteById(id);
    }

    private int calculateTotalCredits(List<String> courseCodes) {
        int total = 0;

        for (int i = 0; i < courseCodes.size(); i++) {
            String code = courseCodes.get(i);
            Optional<Course> courseOptional = courseRepository.findById(code);

            if (courseOptional.isPresent()) {
                total = total + courseOptional.get().getCredits();
            }
        }

        return total;
    }

    private List<String> findMissingPrerequisites(List<String> plannedCourseCodes) {
        List<String> missing = new ArrayList<String>();

        for (int i = 0; i < plannedCourseCodes.size(); i++) {
            String code = plannedCourseCodes.get(i);

            Optional<Course> courseOptional = courseRepository.findById(code);

            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                List<String> prerequisites = course.getPrerequisites();

                for (int j = 0; j < prerequisites.size(); j++) {
                    String prerequisite = prerequisites.get(j);

                    boolean prerequisiteIsAlsoPlanned = plannedCourseCodes.contains(prerequisite);

                    if (!prerequisiteIsAlsoPlanned && !missing.contains(prerequisite)) {
                        missing.add(prerequisite);
                    }
                }
            }
        }

        return missing;
    }

    private void validatePlanRequest(PlanRequest request) {
        if (request.getSemesterName() == null || request.getSemesterName().trim().length() == 0) {
            throw new RuntimeException("Semester name cannot be empty.");
        }

        if (request.getCourseCodes() == null || request.getCourseCodes().size() == 0) {
            throw new RuntimeException("Plan must contain at least one course.");
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

}

package com.rafi.courseplanner.dto;

import java.util.List;

public class PlanResponse {
	
	private Long id;
    private String semesterName;
    private List<String> courseCodes;
    private int totalCredits;
    private List<String> missingPrerequisites;

    public PlanResponse(Long id, String semesterName, List<String> courseCodes,
                        int totalCredits, List<String> missingPrerequisites) {
        this.id = id;
        this.semesterName = semesterName;
        this.courseCodes = courseCodes;
        this.totalCredits = totalCredits;
        this.missingPrerequisites = missingPrerequisites;
    }

    public Long getId() {
        return id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public List<String> getMissingPrerequisites() {
        return missingPrerequisites;
    }

}

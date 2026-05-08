package com.rafi.courseplanner.dto;

import java.util.List;

public class CourseResponse {

	private String code;
	private String title;
	private int credits;
	private List<String> prerequisites;

    public CourseResponse(String code, String title, int credits, List<String> prerequisites) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.prerequisites = prerequisites;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }
	
}

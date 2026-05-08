package com.rafi.courseplanner.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseRequest {
	
	private String code;
	private String title;
	private int credits;
	private List<String> prerequisites;
	
	public CourseRequest() {
		prerequisites = new ArrayList<String>();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredits() {
		return credits;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public List<String> getPrerequisites(){
		return prerequisites;
	}
	
	public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
}

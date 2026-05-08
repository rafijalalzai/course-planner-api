package com.rafi.courseplanner.dto;

import java.util.ArrayList;
import java.util.List;

public class PlanRequest {
	
	private String semesterName;
	private List<String> courseCodes;
	
	public PlanRequest() {
		courseCodes = new ArrayList<String>();
	}
	
	public String getSemesterName() {
		return semesterName;
	}
	
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	
	public List<String> getCourseCodes(){
		return courseCodes;
	}
	
	public void setCouresCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}

}

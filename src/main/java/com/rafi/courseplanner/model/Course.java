package com.rafi.courseplanner.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

	 @Id
	    private String code;

	    private String title;

	    private int credits;

	    @ElementCollection
	    private List<String> prerequisites;

	    public Course() {
	        prerequisites = new ArrayList<String>();
	    }

	    public Course(String code, String title, int credits, List<String> prerequisites) {
	        this.code = code;
	        this.title = title;
	        this.credits = credits;

	        if (prerequisites == null) {
	            this.prerequisites = new ArrayList<String>();
	        } else {
	            this.prerequisites = prerequisites;
	        }
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

	    public List<String> getPrerequisites() {
	        return prerequisites;
	    }

	    public void setPrerequisites(List<String> prerequisites) {
	        this.prerequisites = prerequisites;
	    }
}

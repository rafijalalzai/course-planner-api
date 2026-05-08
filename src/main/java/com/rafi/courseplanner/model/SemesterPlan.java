package com.rafi.courseplanner.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SemesterPlan {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semesterName;

    @ElementCollection
    private List<String> courseCodes;

    public SemesterPlan() {
        courseCodes = new ArrayList<String>();
    }

    public SemesterPlan(String semesterName, List<String> courseCodes) {
        this.semesterName = semesterName;

        if (courseCodes == null) {
            this.courseCodes = new ArrayList<String>();
        } else {
            this.courseCodes = courseCodes;
        }
    }

    public Long getId() {
        return id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<String> courseCodes) {
        this.courseCodes = courseCodes;
    }
}

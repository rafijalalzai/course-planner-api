package com.rafi.courseplanner.controller;


import com.rafi.courseplanner.dto.PlanRequest;
import com.rafi.courseplanner.dto.PlanResponse;
import com.rafi.courseplanner.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
	
	private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public PlanResponse createPlan(@RequestBody PlanRequest request) {
        return planService.createPlan(request);
    }

    @GetMapping
    public List<PlanResponse> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public PlanResponse getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return "Plan deleted successfully with ID: " + id;
    }

}

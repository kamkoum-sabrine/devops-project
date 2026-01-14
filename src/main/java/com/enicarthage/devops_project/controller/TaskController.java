package com.enicarthage.devops_project.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private final MetricsService metricsService;   // <-- ajouter

    // Constructeur pour injecter le service
    public TaskController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello DevOps 👋";
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    // ----------------------------
    // Endpoint /metrics
    // ----------------------------
    @GetMapping("/metrics")
    public Object metrics() {
        return new Object() {
            public final long requestCount = metricsService.getRequestCount();
            public final long avgResponseTimeMs = metricsService.getAvgResponseTime();
        };
    }
}

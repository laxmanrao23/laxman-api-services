package com.laxman.job.server.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/jobs")
//@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

    @GetMapping
    public List<String> getJobs() {
        return List.of(
                "Java Developer",
                "React Developer",
                "Spring Boot Developer"
        );
    }
}

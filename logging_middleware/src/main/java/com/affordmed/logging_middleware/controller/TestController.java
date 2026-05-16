package com.affordmed.logging_middleware.controller;

import com.affordmed.logging_middleware.model.LogRequest;
import com.affordmed.logging_middleware.service.LoggingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping("/hello")
    public String hello() {
        return "Logging Middleware Working";
    }

    @PostMapping("/log")
    public String saveLog(@RequestBody LogRequest request) {

        loggingService.saveLog(request);

        return "Log Saved Successfully";
    }
}
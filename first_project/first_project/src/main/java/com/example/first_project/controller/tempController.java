package com.example.first_project.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class tempController {
    @GetMapping("/follow/{id}")
    public String follow() {
        return "";
    }
}

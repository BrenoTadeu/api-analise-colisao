package com.project.estimate_value.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class OpenApi {
    @GetMapping
    public String Home(){
        return "Hello! this is my api for the company";
    }

}

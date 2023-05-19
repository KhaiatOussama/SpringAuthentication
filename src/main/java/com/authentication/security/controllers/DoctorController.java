package com.authentication.security.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/Doctor-Controller")

public class DoctorController {
    @GetMapping
    public String get(){
        return "Get Doctor";
    }
    @PostMapping
    public String post(){
        return "post Doctor";
    }
    @PutMapping
    public String put(){
        return "put Doctor";
    }
    @DeleteMapping
    public String delete(){
        return "Delete Doctor";
    }
}

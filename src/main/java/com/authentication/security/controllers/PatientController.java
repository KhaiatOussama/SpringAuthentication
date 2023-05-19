package com.authentication.security.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Patient-Controller")
public class PatientController {
    @GetMapping
    public String get(){
        return "Get Patient";
    }
    @PostMapping
    public String post(){
        return "post Patient";
    }
    @PutMapping
    public String put(){
        return "put Patient";
    }
    @DeleteMapping
    public String delete(){
        return "Delete Patient";
    }
}

package com.authentication.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class demoController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("This is a secured endpoint");
    }
}

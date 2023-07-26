package com.thesis.springboot.vetclinicsystemcore.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("api/v1/security")
public class SpringSecurityController {
    
    @GetMapping("/basic-auth")
    public String basicAuthCheck() {
        return "Authentication Successful";
    }

}

package com.dent.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String user() {

        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "index";
    }

    @GetMapping("/dentist")
    public String dentist() {
        return "dentist";
    }

    @GetMapping("/meet")
    public String meet() {
        return "meet";
    }

    @GetMapping("/patient")
    public String patient() {
        return "patient";
    }
}
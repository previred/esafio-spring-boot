package com.previred.nspa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PruebaController {


    @GetMapping
    public String getAll() {
        return "all";
    }

    @PostMapping
    public String create(@RequestBody String test) {
        return test;
    }

}

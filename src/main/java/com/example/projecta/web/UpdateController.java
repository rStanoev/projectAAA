package com.example.projecta.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpdateController {

    @GetMapping("/update")
    public String Update() {
        return "update";
    }
}

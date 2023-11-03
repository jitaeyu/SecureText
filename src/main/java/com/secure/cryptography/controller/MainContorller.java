package com.secure.cryptography.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContorller {

    @GetMapping("/")
    public String index(){
        return "page/index";
    }
}

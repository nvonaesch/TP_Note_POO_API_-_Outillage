package com.ensim.TP3_API.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressSingleController {

    @GetMapping("/adresse")
    public String showBar(){
        return "adresse";
    }   
}

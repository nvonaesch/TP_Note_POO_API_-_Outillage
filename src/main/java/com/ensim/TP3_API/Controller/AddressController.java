package com.ensim.TP3_API.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensim.TP3_API.Model.AddressRepository;

@Controller
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/adresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addressRepository.findAll());
        return "addresses"; 
    }
}
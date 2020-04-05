package com.springframework.petclinic.controllers;

import com.springframework.petclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping({"", "/"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }
    @RequestMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }

}


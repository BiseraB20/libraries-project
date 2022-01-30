package com.dians.lab.dianslab.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req){

        if(req.getSession().getAttribute("user") == null){
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("bodyContent", "Home");

            return "master-template";
        }
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("bodyContent", "Home");

        return "master-template";
    }
}

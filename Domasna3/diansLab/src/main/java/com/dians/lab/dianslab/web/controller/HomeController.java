package com.dians.lab.dianslab.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req){

        if(req.getSession().getAttribute("user") == null){
            model.addAttribute("isLoggedIn", false);
            return "Home";
        }
        model.addAttribute("isLoggedIn", true);

        return "Home";
    }
}

package com.dians.lab.dianslab.web.controller;


import com.dians.lab.dianslab.model.User;
import com.dians.lab.dianslab.model.exceptions.InvalidUserCredentialsException;
import com.dians.lab.dianslab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest req, Model model){
        User user = null;
        try {
            user = this.userService.login(req.getParameter("username"), req.getParameter("password"));

            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("username", user.getUsername());
            return "redirect:/user";
        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}

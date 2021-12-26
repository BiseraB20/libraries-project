package com.dians.lab.dianslab.web.controller;





import com.dians.lab.dianslab.model.exceptions.InvalidArgumentsException;
import com.dians.lab.dianslab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password){
        try {
            this.userService.register(name, surname, username, password);
            return "redirect:/login";
        }catch (InvalidArgumentsException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

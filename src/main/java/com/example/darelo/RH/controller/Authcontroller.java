package com.example.darelo.RH.controller;

import org.springframework.ui.Model;
import com.example.darelo.RH.service.OtpService;
import com.example.darelo.RH.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Authcontroller {
    private final UserService userService;
    private final OtpService otpService;

    public Authcontroller(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUSer(@RequestParam String fullname, @RequestParam String email, @RequestParam String password, Model model){
        userService.creerUser(fullname, email, password, "EMPLOYEE");
        otpService.generateOtp(email);
        model.addAttribute("email", email);
        return "verify-otp";
    }

    @GetMapping("/verify-otp")
    public String verifyOtpPage(){
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String code, Model model){
        if (otpService.verifyotp(email, code)){
            model.addAttribute("message", "connexion validée !");
            return "dashboard";
        }else{
            model.addAttribute("error", "code OTP invalide ou expiré !");
            return "verify-otp";
        }
    }
}

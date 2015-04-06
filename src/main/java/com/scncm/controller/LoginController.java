package com.scncm.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginFormAfterRequest(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "forbidden", required = false) String forbidden,
            @RequestParam(value = "register-successful", required = false) String registerSuccessful,
            Model model) {
        // todo check here for admin or moderator or use in JSP sec tag!!!

        // if not authenticated, return to the wall page
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/wall");
        }

        ModelAndView mv = new ModelAndView("home");
        String constraintError = (String) model.asMap().get("error");

        if (constraintError != null) {
            mv.addObject("showRegistrationForm", true);
            switch (constraintError) {
                case "invalidEmail": {
                    mv.addObject("error", "Please enter a valid email");
                    return mv;
                }
                case "usernameTooShort": {
                    mv.addObject("error", "Please add a minimum 4 letters username");
                    return mv;
                }
                case "invalidPassword": {
                    mv.addObject("error", "Please add a minimum 6 letter password");
                    return mv;
                }
                case "emailAlreadyUsed": {
                    mv.addObject("error", "The entered email is already used");
                    return mv;
                }
                case "usernameAlreadyUsed": {
                    mv.addObject("error", "The entered username is already used");
                    return mv;
                }
                case "emptyFields": {
                    mv.addObject("error", "Please fill all the fields");
                    return mv;
                }
                case "errorCreatingUser": {
                    mv.addObject("error", "An error occurred during account creation. Please try again");
                    return mv;
                }
            }
        }

        mv.addObject("showRegistrationForm", false);

        if (error != null) {
            mv.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            mv.addObject("msg", "You've been logged out successfully.");
        }

        if (forbidden != null) {
            mv.addObject("notAllowed", "Please login first");
        }

        if (registerSuccessful != null) {
            mv.addObject("registerSuccessful", "Register successfully");
        }

        return mv;
    }
}
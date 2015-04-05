package com.scncm.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginFormAfterRequest(
            @RequestParam(value = "error",  required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "forbidden", required = false) String forbidden,
            @RequestParam(value = "register-successful", required = false) String registerSuccessful) {
        // todo check here for admin or moderator or use in JSP sec tag!!!

        // if authenticated, return to the wall page
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/wall");
        }


        ModelAndView mv = new ModelAndView("home");

        if (error != null) {
            mv.addObject("error", true);
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
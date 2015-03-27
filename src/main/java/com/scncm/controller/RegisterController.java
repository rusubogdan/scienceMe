package com.scncm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


public class RegisterController {


    @RequestMapping(value = "/register", method = RequestMethod.GET)

    public ModelAndView registerFormAfterRequest(


            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "firstName", required = true) String lastName,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "gender", required = true) String gender) {

        // i don't know what i`m doing
        ModelAndView mv = new ModelAndView("registerForm");

        if (email == null) {
            mv.addObject("missing", "Please enter your email.");
        }

        return mv;
    }


}
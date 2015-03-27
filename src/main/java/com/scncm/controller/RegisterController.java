package com.scncm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


public class RegisterController {


    @RequestMapping(value = "/register", method = RequestMethod.GET)

    public ModelAndView registerFormAfterRequest(


            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "firstName", required = false) String lastName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "gender", required = false) String gender) {

        //
        ModelAndView mv = new ModelAndView("registerForm");

        if (email == null) {
            mv.addObject("missing", "Please enter your email.");
        }

        return mv;
    }


}
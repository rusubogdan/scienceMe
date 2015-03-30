// pending port to LoginController

package com.scncm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView registerFormAfterRequest(){

        ModelAndView mv = new ModelAndView("registerForm");

        return mv;
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView submit(

            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "gender", required = false) String gender) {

        ModelAndView mv = new ModelAndView("loginForm");
        if (email == null) {
            mv.addObject("missing", "Please enter your email.");
        }
        return new ModelAndView("submit");
    }

}
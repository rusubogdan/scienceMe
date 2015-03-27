package com.scncm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mihai7 on 3/27/2015.
 */
public class RegisterController {


    @RequestMapping(value = "/register", method= RequestMethod.GET)

    public ModelAndView registerForm(


            @RequestParam(value = "error",  required = false) String error,


    )






}

package com.scncm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        // todo check here for admin or moderator
        return new ModelAndView("loginForm");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        // todo check here for admin or moderator
        return new ModelAndView("logout");
    }

    // todo delete these
    @RequestMapping(value = "/moderator", method = RequestMethod.GET)
    public ModelAndView moderatorPage() {
        return new ModelAndView("moderation");
    }

}
package com.scncm.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam(value = "forbidden", required = false) String forbidden) {
        // todo check here for admin or moderator or use in JSP sec tag!!!

        ModelAndView mv = new ModelAndView("home"); // revine in home, am scos loginForm

        if (error != null) {
            mv.addObject("error", true);
        }

        if (logout != null) {
            mv.addObject("msg", "You've been logged out successfully.");
        }

        if (forbidden != null) {
            mv.addObject("notAllowed", "Please login first");
        }

        return mv;
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
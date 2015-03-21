package com.scncm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by bogdan on 3/19/2015.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        // todo check here for admin or moderator
        return new ModelAndView("loginForm");
    }

    @RequestMapping(value = "/successLogin", method = RequestMethod.GET)
    public ModelAndView successLogin() {
        return new ModelAndView("successLogin");
    }

    // todo delete these
    @RequestMapping(value = "/moderator", method = RequestMethod.GET)
    public ModelAndView moderatorPage() {
        return new ModelAndView("moderation");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView firstAdminPage() {
        return new ModelAndView("admin");
    }
}

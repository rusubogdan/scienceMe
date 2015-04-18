package com.scncm.controller;

import com.scncm.model.User;
import com.scncm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.SecurityContext;

@Controller
@RequestMapping("user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{username}")
    public ModelAndView userProfile(@PathVariable(value = "username") String username) {
        ModelAndView mv = new ModelAndView("userProfile");
        User loggedInUser = userService.getUserByUsername(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/404");
        }

        mv.addObject("userName", authentication.getName());
        mv.addObject("loggedInUser", loggedInUser);

        return mv;
    }
}

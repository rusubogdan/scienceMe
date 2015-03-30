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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{username}")
    public ModelAndView userProfile(@PathVariable(value = "username") String username,
                                    HttpServletResponse httpServletResponse) {
        ModelAndView mv = new ModelAndView("userProfile");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
            try {
                httpServletResponse.sendRedirect("/login?forbidden");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        User user = userService.getUserByEmail(authentication.getName());

        mv.addObject("user", user);

        return mv;
    }
}

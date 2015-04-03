package com.scncm.controller;

import com.scncm.helpers.SignUpForm;
import com.scncm.model.User;
import com.scncm.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister (
            @RequestParam(value = "email") String email,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpSession httpSession) {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(email);
        signUpForm.setUsername(username);
        signUpForm.setPassword(password);
        ModelAndView mv = new ModelAndView("redirect:/login?register-successful");

        // todo validations for email and password

        User userByEmail = userService.getUserByEmail(signUpForm.getEmail());

        if (userByEmail != null) {
            return new ModelAndView("redirect:/login?duplicateEmail");
        }

        User user = userService.fromSignUpForm(signUpForm, false);

//        Boolean success = userService.sendSignUpEmail(user);

//        if (success) {
            Integer createdUserId = userService.addUser(user);

            if (createdUserId == -1) {
                logger.warn("User could not be created. Redirect to login error");

                return new ModelAndView("redirect:/login?error");
            } else {
                logger.debug("User created (unconfirmed): " + user);
            }
//        }

        // authenticate user and redirect to wall

        return mv;
    }
}

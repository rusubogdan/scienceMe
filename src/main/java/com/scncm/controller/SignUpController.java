package com.scncm.controller;

import com.scncm.helpers.AppUtil;
import com.scncm.helpers.SignUpForm;
import com.scncm.model.User;
import com.scncm.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister (
            @RequestParam(value = "email")    String email,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        // todo add modelAttribute for signUpForm
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(email);
        signUpForm.setUsername(username);
        signUpForm.setPassword(password);

        ModelAndView mv = new ModelAndView("redirect:/login");

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "emptyFields");
            return mv;
        }

        if (!AppUtil.isValidEmail(email)) {
            redirectAttributes.addFlashAttribute("error", "invalidEmail");
            return mv;
        }

        if (username.length() <= 3) {
            redirectAttributes.addFlashAttribute("error", "usernameTooShort");
            return mv;
        }

        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "invalidPassword");
            return mv;
        }

        User userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null) {
            redirectAttributes.addFlashAttribute("error", "emailAlreadyUsed");
            return mv;
        }

        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername != null) {
            redirectAttributes.addFlashAttribute("error", "usernameAlreadyUsed");
            return mv;
        }

        User user = userService.fromSignUpForm(signUpForm, false);

//        Boolean success = userService.sendSignUpEmail(user);

//        if (success) {
            Integer createdUserId = userService.addUser(user);

            if (createdUserId == -1) {
                logger.warn("User could not be created. Redirect to login error");
                redirectAttributes.addFlashAttribute("error", "errorCreatingUser");

                return mv;
            } else {
                logger.debug("User created (unconfirmed): " + user);
            }
//        }

        // authenticate user and redirect to wall
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return new ModelAndView("redirect:/wall");
    }
}

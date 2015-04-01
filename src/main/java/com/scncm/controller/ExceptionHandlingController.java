package com.scncm.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.ControllerAdvice;

// dependency in pom which may cause problems

//@ControllerAdvice
public class ExceptionHandlingController {

    // Total control - setup a model and return the view name yourself. Or consider
    // sub-classing ExceptionHandlerExceptionResolver .
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
//        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mv = new ModelAndView("serverError");
        mv.addObject("exception", exception);
        mv.addObject("url", req.getRequestURL());

        return mv;
    }
}

package com.scncm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionHandlingController {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    /*@ExceptionHandler(value = {NoHandlerFoundException.class})
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        logger.error("Request: " + request.getRequestURL() + " raised " + e);

        return new ModelAndView("redirect:/404");
    }
*/
    // Total control - setup a model and return the view name yourself. Or consider
    // sub-classing ExceptionHandlerExceptionResolver
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        return new ModelAndView("redirect:/505");
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleException (NoSuchRequestHandlingMethodException ex) {
        return new ModelAndView("redirect:/505");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleExceptiond (NoHandlerFoundException ex) {
        return new ModelAndView("redirect:/505");
    }*/
}

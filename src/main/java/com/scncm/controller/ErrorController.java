package com.scncm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "")
public class ErrorController {
    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "/505")
    public ModelAndView serve505ErrorPage (HttpServletRequest request) {

//        mv.addObject("url", request.getParameter("url"));
//        mv.addObject("exception", request.getParameter("exception"));

        return new ModelAndView("505");
    }

    @RequestMapping(value = "/404")
    public ModelAndView serve404ErrorPage (HttpServletRequest request) {

//        mv.addObject("url", request.getParameter("url"));
//        mv.addObject("exception", request.getParameter("exception"));

        return new ModelAndView("404");
    }

}

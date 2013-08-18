package com.lyaotian.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Yaotian Leung
 * Date: 8/18/13
 * Time: 1:40 PM
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("home")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}

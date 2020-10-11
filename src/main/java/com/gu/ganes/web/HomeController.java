package com.gu.ganes.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gu.sc
 */
@Controller
public class HomeController {

  @GetMapping("thymeleaf")
  private String thymeleaf(ModelMap model) {
    model.addAttribute("name" , "gu");
    return "thymeleaf";
  }
}

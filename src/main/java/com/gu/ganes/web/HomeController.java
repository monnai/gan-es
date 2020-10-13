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

  @GetMapping(value = {"index", "/"})
  public String index() {
    return "index";
  }

  @GetMapping("403")
  public String notAccess() {
    return "403";
  }
  @GetMapping("404")
  public String notFoundPage() {
    return "404";
  }
  @GetMapping("500")
  public String internalErrorPage() {
    return "500";
  }

  @GetMapping("logout")
  public String logout() {
    return "logout";
  }
}

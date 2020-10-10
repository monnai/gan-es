package com.gu.ganes.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gu.sc
 */
@RestController
public class TestController {

  @RequestMapping("test")
  private String doTest() {
    return "success";
  }
}

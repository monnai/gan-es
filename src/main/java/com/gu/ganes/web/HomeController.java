package com.gu.ganes.web;

import com.gu.ganes.base.ApiResponse;
import com.gu.ganes.base.LoginUserUtil;
import com.gu.ganes.service.ISmsService;
import com.gu.ganes.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gu.sc
 */
@Controller
public class HomeController {

  @Autowired
  private ISmsService smsService;

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

  @GetMapping("/logout/page")
  public String logoutPage() {
    return "logout";
  }

  @GetMapping(value = "sms/code")
  @ResponseBody
  public ApiResponse smsCode(@RequestParam("telephone") String telephone) {
   /* if (!LoginUserUtil.checkTelephone(telephone)) {
      return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "请输入正确的手机号");
    }*/
    ServiceResult<String> result = smsService.sendSms(telephone);
    if (result.isSuccess()) {
      return ApiResponse.ofSuccess("");
    } else {
      return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
    }

  }
}

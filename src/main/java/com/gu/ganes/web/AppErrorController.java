package com.gu.ganes.web;

import com.gu.ganes.base.ApiResponse;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 统一异常处理
 *
 * @author gu.sc
 */

@Controller
@ControllerAdvice
public class AppErrorController extends AbstractErrorController {


  public AppErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

/*  public AppErrorController(ErrorAttributes errorAttributes,
      List<ErrorViewResolver> errorViewResolvers) {
    super(errorAttributes, errorViewResolvers);
  }*/

  @RequestMapping(value = "${server.error.path}", produces = "text/html")
  private String error(HttpServletRequest request, HttpServletResponse response) {
    int status = response.getStatus();
    switch (status) {
      case 404:
        return "404";
      case 403:
        return "403";
      case 500:
        return "500";
      default:
        return "index";
    }
  }

  /**
   * 除Web页面外的错误处理，比如Json/XML等
   */

  @RequestMapping("${server.error.path}")
  @ResponseBody
  public ApiResponse errorApiHandler(HttpServletRequest request) {
    ServletWebRequest webRequest = new ServletWebRequest(request);
    Map<String, Object> attr = super.getErrorAttributes(request,false);
    int status = getStatus(request).value();
    return ApiResponse.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
  }

  @Override
  public HttpStatus getStatus(HttpServletRequest request) {
    Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (status != null) {
      return HttpStatus.valueOf(status);
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}


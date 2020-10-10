package com.gu.ganes.web;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gu.sc
 */
@RestController
public class TestController {

  @Autowired
  private DataSource dataSource;
  @RequestMapping("test")
  private String doTest() throws SQLException {
    Connection connection = dataSource.getConnection();
    return "success";
  }
}

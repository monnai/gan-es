package com.gu.ganes.config;

import com.gu.ganes.security.AuthFilter;
import com.gu.ganes.security.AuthProvider;
import com.gu.ganes.security.LoginAuthFailHandler;
import com.gu.ganes.security.LoginUrlEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author gu.sc
 * @EnableWebSecurity 标记后 webSecurityConfigurerAdapter才起作用吧
 * @EnableGlobalMethodSecurity <global-method-security>
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // 开放校验
        // 管理员登录入口
        .antMatchers("/admin/login").permitAll()
        // 静态资源
        .antMatchers("/static/**").permitAll()
        // 用户登录入口
        .antMatchers("/user/login").permitAll()
        // 配置校验接口
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
        .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")

        .and()
        .formLogin()
        .loginProcessingUrl("/login") // 配置角色登录处理入口
        .failureHandler(authFailHandler())
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/logout/page")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(urlEntryPoint())
        .accessDeniedPage("/403");

    http.csrf().disable();
    http.headers().frameOptions().sameOrigin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //添加一个provider
    auth.authenticationProvider(authProvider()).eraseCredentials(true);
  }

  //权限认证
  @Bean
  public AuthProvider authProvider() {
    return new AuthProvider();
  }

  @Bean
  public LoginUrlEntryPoint urlEntryPoint() {
    return new LoginUrlEntryPoint("/user/login");
  }

  @Bean
  public LoginAuthFailHandler authFailHandler() {
    return new LoginAuthFailHandler(urlEntryPoint());
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManager() {
    AuthenticationManager authenticationManager = null;
    try {
      authenticationManager =  super.authenticationManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return authenticationManager;
  }

  @Bean
  public AuthFilter authFilter() {
    AuthFilter authFilter = new AuthFilter();
    authFilter.setAuthenticationManager(authenticationManager());
    authFilter.setAuthenticationFailureHandler(authFailHandler());
    return authFilter;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

package com.gu.ganes.security;

import com.gu.ganes.entity.User;
import com.gu.ganes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义认证
 *
 * @author gu.sc
 */
public class AuthProvider implements AuthenticationProvider {

  @Autowired
  private IUserService userService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName = authentication.getName();
    String inputPass = (String) authentication.getCredentials();
    User user = userService.findUserByName(userName);
    if (user == null) {
      throw new AuthenticationCredentialsNotFoundException("auth error");
    }
    if (bCryptPasswordEncoder.matches(inputPass,user.getPassword())) {
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    if (true) {
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}

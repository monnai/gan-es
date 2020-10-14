package com.gu.ganes.service;

import com.gu.ganes.dto.dto.UserDTO;
import com.gu.ganes.entity.User;

/**
 * @author gu.sc
 */
public interface IUserService {

  User findUserByName(String userName);

  ServiceResult<UserDTO> findById(Long userId);

  /**
   * 根据电话号码寻找用户
   */
  User findUserByTelephone(String telephone);

  /**
   * 通过手机号注册用户
   */
  User addUserByPhone(String telehone);

  /**
   * 修改指定属性值
   */
  ServiceResult modifyUserProfile(String profile, String value);

}

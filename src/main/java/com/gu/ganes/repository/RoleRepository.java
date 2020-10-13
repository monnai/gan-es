package com.gu.ganes.repository;


import com.gu.ganes.entity.Role;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * 角色数据DAO
 * @author Administrator
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findRolesByUserId(Long userId);
}

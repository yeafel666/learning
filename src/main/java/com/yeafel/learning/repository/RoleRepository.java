package com.yeafel.learning.repository;

import com.yeafel.learning.dataobject.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kangyifan on 2018/11/12 13:11
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    /** 根据id查询角色 .*/
    Role findByRoleId(Long roleId);

    @Transactional
    @Query(value = "select * from role where if(?1 !='',role_name=?1,1=1)",nativeQuery = true)
    Page<Role> finRolesIfRolenameIsNotNull(String roleName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from role where if(?1 !='',role_name=?1,1=1)",nativeQuery = true)
    Integer countRoleForPage(String roleName);


    @Transactional
    void deleteByRoleId(Long roleId);

    /** 根据角色名称来查询角色.*/
    Role findByRoleName(String roleName);
}

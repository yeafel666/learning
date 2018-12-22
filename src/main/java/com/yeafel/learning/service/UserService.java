package com.yeafel.learning.service;

import com.yeafel.learning.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kangyifan on 2018/11/12 12:55
 */
public interface UserService {

    /** 创建用户. */
    UserDTO create(UserDTO userDTO);

    /** 查询单个用户. */
    UserDTO findByUserId(Long userId);

    /** 根据人员编号查询用户. */
    UserDTO findByUserNo(String userNo);


    /** 查找所有用户. */
    Page<UserDTO> findAll(Pageable pageable);

    /** 服从Layui查询接口 .*/
    Page<UserDTO> findUsersIfUsernameIsNotNull(String username,Pageable pageable);

    /** 计算用户总数 */
    Integer countUsersByUserIdIsNotNull();

    /**   修改用户信息. */
    UserDTO updateUser(UserDTO userDTO);


    /** 删除用户. */
    void deleteByUserId(Long userId);


    /** 为了分页查询总数 .*/
    Integer countUserForPage(String username);


}

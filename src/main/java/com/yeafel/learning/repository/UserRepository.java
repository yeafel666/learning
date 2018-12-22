package com.yeafel.learning.repository;

import com.yeafel.learning.dataobject.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kangyifan on 2018/11/12 12:48
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /** 根据id查询用户 .*/
    User findByUserId(Long userId);

    /** 根据人员编号查询用户. */
    User findByUserNo(String userNo);

    /** 计算总用户数 */
    Integer countUsersByUserIdIsNotNull();

    /** 删除用户 */
    void deleteByUserId(Long userId);

    @Transactional
    @Query(value = "select * from user where if(?1 !='',username=?1,1=1)",nativeQuery = true)
    Page<User> findUsersIfUsernameIsNotNull(String username, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from user where if(?1 !='',username=?1,1=1)",nativeQuery = true)
    Integer countUserForPage(String username);
}

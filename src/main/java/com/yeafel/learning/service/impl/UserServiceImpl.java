package com.yeafel.learning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yeafel.learning.dataobject.User;
import com.yeafel.learning.dto.UserDTO;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.repository.UserRepository;
import com.yeafel.learning.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.learning.converter.User2UserDTOConverter.convert;

/**
 * Created by kangyifan on 2018/11/12 13:00
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO create(UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        User user1 = userRepository.save(user);
        return convert(user1);
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null){
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }

        UserDTO userDTO = convert(user);

        return userDTO;
    }

    @Override
    public UserDTO findByUserNo(String userNo) {
        User user = userRepository.findByUserNo(userNo);
        if (user == null){
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }
        return convert(userRepository.findByUserNo(userNo));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage =  userRepository.findAll(pageable);

        List<UserDTO> userDTOList = convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;
    }

    @Override
    public Page<UserDTO> findUsersIfUsernameIsNotNull(String username, Pageable pageable) {
        Page<User> userPage = userRepository.findUsersIfUsernameIsNotNull(username,pageable);

        List<UserDTO> userDTOList = convert(userPage.getContent());

        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());

        return userDTOPage;

    }

    @Override
    public Integer countUsersByUserIdIsNotNull() {
        return userRepository.countUsersByUserIdIsNotNull();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user;
        if (userDTO.getUserId() != null){
            user = userRepository.findByUserId(userDTO.getUserId());
        }else {
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }

        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(userDTO,user,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        User user1 = userRepository.save(user);
        return convert(user1);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public Integer countUserForPage(String username) {
        return userRepository.countUserForPage(username);
    }
}

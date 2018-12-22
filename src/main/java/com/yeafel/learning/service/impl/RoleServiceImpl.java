package com.yeafel.learning.service.impl;

import com.yeafel.learning.dataobject.Action;
import com.yeafel.learning.dataobject.ActionRole;
import com.yeafel.learning.dataobject.Role;
import com.yeafel.learning.dataobject.UserRole;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.repository.ActionRepository;
import com.yeafel.learning.repository.ActionRoleRepository;
import com.yeafel.learning.repository.RoleRepository;
import com.yeafel.learning.repository.UserRoleRepository;
import com.yeafel.learning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *   对角色表的操作
 * Created by kangyifan on 2018/11/12 13:15
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ActionRoleRepository actionRoleRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleId(Long roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        if (role == null){
            throw new LearningException(ResultEnum.ROLE_NOT_EXIST);
        }
        return role;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage;
    }

    /**
     *  查看某个用户是否拥有某个角色
     * @param userId
     * @param roleId
     */
    @Override
    public Boolean checkUserRole(Long userId, Long roleId) {
        List<UserRole> userRoleList = userRoleRepository.findUserRolesByUserId(userId);
        for (UserRole userRole : userRoleList){
            if (userRole.getRoleId() == roleId){
                return true;
            }
        }

        return false;
    }

    /**
     *  查询某个角色拥有哪些功能.
     * @param roleId
     * @return
     */
    @Override
    public List<Action> findActionsByRoleId(Long roleId) {
        List<ActionRole> actionRoleList = actionRoleRepository.findActionRolesByRoleId(roleId);
        List<Action> actionList = new ArrayList<>();
        for (ActionRole actionRole : actionRoleList){
            Action action = actionRepository.findByActionId(actionRole.getActionId());
            actionList.add(action);
        }

        return actionList;
    }
    @Override
    public Page<Role> finRolesIfRolenameIsNotNull(String roleName, Pageable pageable) {
        Page<Role> rolePage =  roleRepository.finRolesIfRolenameIsNotNull(roleName,pageable);

        return rolePage;
    }

    @Override
    public Integer countRoleForPage(String roleName) {
        Integer count = roleRepository.countRoleForPage(roleName);

        return count;
    }

    @Override
    public Role updateRole(Role role) {
        Role result;
        if (role.getRoleId()!= null){
            result = roleRepository.save(role);
        }else {
            throw new LearningException(ResultEnum.ROLE_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        roleRepository.deleteByRoleId(roleId);
    }
}

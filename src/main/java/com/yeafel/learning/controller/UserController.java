package com.yeafel.learning.controller;

import com.yeafel.learning.dto.UserDTO;
import com.yeafel.learning.enums.ResultEnum;
import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.service.RoleService;
import com.yeafel.learning.service.UserService;
import com.yeafel.learning.utils.ResultVOUtil;
import com.yeafel.learning.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by kangyifan on 2018/11/5 15:39
 */
@RestController
@RequestMapping(value = "/user",produces = "application/json")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     *   登录
     */
    @GetMapping("/login")
    public ResultVO<Null> login(@RequestParam(value = "userNo") String userNo,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "roleId")Long roleId,
                                HttpSession session
    ){


        //人员编号和密码去和数据库里的数据匹配

        //手动处理异常
        //1、查询用户
        UserDTO userDTO = userService.findByUserNo(userNo);
        if (userDTO == null){
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }
        else if (!userDTO.getPassword().equals(password )){
            throw new LearningException(ResultEnum.PASSWORD_ERROR);
        }else {
            Boolean bl  = roleService.checkUserRole(userDTO.getUserId(),roleId);
            if (bl == true){
                session.setAttribute("user",userDTO);
                session.setAttribute("roleId",roleId);
//                session.invalidate();
                return  ResultVOUtil.success();
            }else {
                return ResultVOUtil.error(ResultEnum.ROLE_REJECT.getCode(),ResultEnum.ROLE_REJECT.getMessage());
            }

        }


    }


    /**
     *      登录成功之后获取登录的用户信息并显示到页面
     */
    @GetMapping("/getUserInfo")
    public ResultVO<UserDTO> getUserInfo(HttpSession session){

        UserDTO userDTO;
        userDTO = (UserDTO) session.getAttribute("user");
//        session.invalidate();
        return ResultVOUtil.success(userDTO);
    }


    //注销用户
    @GetMapping("/exitUser")
    public ResultVO<Null> exitUser(HttpSession session){
        session.invalidate();
        return ResultVOUtil.success();
    }


    /**
     *  用户列表
     */
    @GetMapping("/list")
    public ResultVO<List<UserDTO>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                        UserDTO userDTO) throws UnsupportedEncodingException {
        // HttpServletResponse response)


        Integer count = userService.countUserForPage(userDTO.getUsername());

        /** 此处代码作用：  从前端传回来的username值会乱码，在这里处理掉乱码问题.*/
        if (userDTO.getUsername() != null) {
            String fname =  URLDecoder.decode(userDTO.getUsername() , "utf-8");
            userDTO.setUsername(fname);
        }


        PageRequest request = PageRequest.of(page-1,limit);
        Page<UserDTO> userDTOPage = userService.findUsersIfUsernameIsNotNull(userDTO.getUsername(),request);

//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(userDTOPage.getContent(),count);
    }


    /**
     * 保存用户，编辑与新增公用
     */
    @PostMapping("/save")
    public ResultVO<Null> save(UserDTO userDTO){

        if (userDTO != null && userDTO.getUserId() != null){
            UserDTO updateResult = userService.updateUser(userDTO);
            if (updateResult == null){
                throw new LearningException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            UserDTO createResult = userService.create(userDTO);
            if (createResult == null){
                throw new LearningException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }



    @PostMapping("/updateUser")
    public ResultVO<Null> updateUser(Long userId){

        UserDTO  userDTO = userService.findByUserId(userId);
        if (userDTO == null){
            throw new LearningException(ResultEnum.USER_NOT_EXIST);
        }

        return ResultVOUtil.success(userDTO);
    }



    @GetMapping("/findByUserId")
    public ResultVO<UserDTO> findByUserId(Long userId){
        UserDTO userDTO = userService.findByUserId(userId);
        return ResultVOUtil.success(userDTO);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        /*判断时单行删除还是批量删除*/
        if (delitems != null) {
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                userService.deleteByUserId(a);
            }
        } else {
            userService.deleteByUserId(id);
        }
        return ResultVOUtil.success();
    }


    /**
     *  用户列表
     */
    @GetMapping("/personal")
    public ResultVO<List<UserDTO>> personal(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                            UserDTO userDTO,
                                            HttpSession session){
        // HttpServletResponse response)

        UserDTO u = (UserDTO) session.getAttribute("user");
        userDTO.setUsername(u.getUsername());
        Integer count = userService.countUserForPage(userDTO.getUsername());

        PageRequest request = PageRequest.of(page-1,limit);
        Page<UserDTO> userDTOPage = userService.findUsersIfUsernameIsNotNull(userDTO.getUsername(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(userDTOPage.getContent(),count);
    }

}

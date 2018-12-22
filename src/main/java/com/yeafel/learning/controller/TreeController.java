package com.yeafel.learning.controller;

import com.yeafel.learning.service.TreeService;
import com.yeafel.learning.tree.ActionNode;
import com.yeafel.learning.utils.ResultVOUtil;
import com.yeafel.learning.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *      树形菜单控制层
 * Created by kangyifan on 2018/9/18 13:56
 */
@RestController
@RequestMapping("/tree")
public class TreeController {


    @Autowired
    private TreeService treeService;


    @GetMapping("/getTree")
    public ResultVO<String> list(HttpSession session){
        Long roleId = (Long)session.getAttribute("roleId");
        ActionNode actionNode = treeService.formTreeByRole(roleId);
        actionNode.setSpread(true);


        return ResultVOUtil.success(actionNode);
    }




}

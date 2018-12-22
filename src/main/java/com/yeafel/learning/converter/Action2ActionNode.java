package com.yeafel.learning.converter;

import com.yeafel.learning.dataobject.Action;
import com.yeafel.learning.tree.ActionNode;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  功能类型转换
 * Created by kangyifan on 2018/9/23 3:10
 */
public class Action2ActionNode {

    public static ActionNode convert(Action action){

        ActionNode actionNode = new ActionNode();
        BeanUtils.copyProperties(action,actionNode);
        return actionNode;
    }

    public static List<ActionNode> convert(List<Action> actionList){

        return actionList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}

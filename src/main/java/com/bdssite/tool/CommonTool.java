package com.bdssite.tool;

import com.bdssite.modules.usermanage.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by D on 2017/3/13.
 */
public class CommonTool {
    /**
     * 在控制器中调用以获取当前User实体
     * @return User实体
     */
    public static UserInfo getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        return (UserInfo)currentUser.getPrincipal();
    }

    /**
     * 将iterable转换为list的工具方法
     * @param iterable  欲转换的iterable
     * @param <S>   iterable对应的实体类
     * @return  由iterable得出的list
     */
    public static <S> List<S> iterableToList(Iterable<S> iterable){
        List<S> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
    /**
     * 将带逗号的字符串分割成字符串数组
     */
    public static String[] stringToStringArray(String string){
        return string.split(",|，");
    }



}

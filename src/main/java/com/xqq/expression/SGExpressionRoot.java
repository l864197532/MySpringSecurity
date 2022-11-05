package com.xqq.expression;



import com.xqq.enrity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
public class SGExpressionRoot {
//自定义权限
    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPremissions();
        System.out.println("==============》获取自定义权限");
        //判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}

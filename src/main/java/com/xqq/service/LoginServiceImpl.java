package com.xqq.service;


import com.xqq.enrity.LoginUser;
import com.xqq.enrity.User;
import com.xqq.utils.JwtUtil;
import com.xqq.utils.RedisCache;
import com.xqq.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseVo login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("认证失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        System.out.println("登录成功，生成jwt并返回给前端==>"+jwt);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseVo(200,"登录成功",map);
    }

    @Override
    public ResponseVo logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        String rediskey="login:"+userid;
        redisCache.deleteObject(rediskey);
        return new ResponseVo(200,"注销成功");
    }


}

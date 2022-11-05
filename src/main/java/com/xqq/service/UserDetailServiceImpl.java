package com.xqq.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.xqq.enrity.LoginUser;
import com.xqq.enrity.User;
import com.xqq.mapper.MenuMapper;
import com.xqq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("数据库中查询用户信息===>"+username);
        LambdaQueryWrapper<User> queryWrapper= new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUserName,username);

        //查询用户对象信息
        User user = (User) userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("密码错误");
        }
        //TODO 查询对应权限信息
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        System.out.println("返回LoginUser=====>");
        //把数据封装UserDetails
        return new LoginUser(user,list);
    }
}

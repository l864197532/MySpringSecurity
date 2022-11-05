package com.xqq.filters;


import com.xqq.enrity.LoginUser;
import com.xqq.utils.JwtUtil;
import com.xqq.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        Claims claims = JwtUtil.parseJWT(token);
        String userid = claims.getSubject();
        //从redis中获取信息
        String rediskey="login:"+userid;

        LoginUser loginUser = redisCache.getCacheObject(rediskey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        System.out.println("token校验成功");
        System.out.println("=====================将用户权限封装Authentication============================");
        System.out.println(Arrays.toString(loginUser.getAuthorities().toArray()));
        System.out.println("=====================将用户权限封装Authentication============================");

        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}

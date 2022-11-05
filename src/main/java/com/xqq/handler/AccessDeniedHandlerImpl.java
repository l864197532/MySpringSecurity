package com.xqq.handler;

import com.alibaba.fastjson.JSON;
import com.xqq.utils.WebUtils;
import com.xqq.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        ResponseVo result = new ResponseVo(HttpStatus.FORBIDDEN.value(),"您的权限不足");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
    //授权失败处理器

//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"您的权限不足");
//        String json = JSON.toJSONString(result);
//        //处理异常
//        WebUtils.renderString(response,json);
//    }
}

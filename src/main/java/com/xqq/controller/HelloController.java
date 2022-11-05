package com.xqq.controller;

import com.xqq.vo.ResponseVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello/0")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/hello/1")
    @PreAuthorize("hasAnyAuthority('system:dept:index')")
    public String hello1(){
        return "hello1";
    }

    @RequestMapping("/hello/2")
    @PreAuthorize("@ex.hasAuthority('sy')")
    public String hello2(){
        return "hello2";
    }

    @RequestMapping("/hello/3")
    @PreAuthorize("hasAuthority('syste5')")
    public String hello3(){
        return "hello3";
    }

    @RequestMapping("/testCors")
    @PreAuthorize("hasAuthority('testCors')")
    public ResponseVo testCors(){
        return new ResponseVo(200,"testCors");
    }
}

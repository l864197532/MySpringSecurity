package com.xqq.controller;

import com.xqq.enrity.User;
import com.xqq.service.LoginService;
import com.xqq.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping ("/user/login")
    public ResponseVo login(@RequestBody User user){
        return loginService.login(user);
    }
    @RequestMapping("/user/logout")
    public ResponseVo logout(){
        return loginService.logout();
    }
}

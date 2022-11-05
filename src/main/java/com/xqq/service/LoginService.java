package com.xqq.service;

import com.xqq.enrity.User;
import com.xqq.mapper.LoginMapper;
import com.xqq.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface LoginService {


    ResponseVo login(User user);


    ResponseVo logout();
}

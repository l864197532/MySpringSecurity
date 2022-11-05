package com.xqq;

import com.xqq.enrity.User;
import com.xqq.mapper.MenuMapper;
import com.xqq.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void testMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(Arrays.toString(users.toArray()));
    }
    @Test
    public void testSelect(){
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(Arrays.toString(list.toArray()));
    }
}

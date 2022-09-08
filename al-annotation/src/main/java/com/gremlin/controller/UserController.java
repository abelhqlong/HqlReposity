package com.gremlin.controller;

import com.gremlin.annotation.CustomLog;
import com.gremlin.vo.req.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserController
 * @author: gremlin
 * @version: 1.0.0
 * @description:
 * @date: 2022/8/14 23:31
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @CustomLog(notes = "获取用户信息,测试")
    @PostMapping("getUserInfo")
    public Object getUserInfo(@RequestBody User user){
        List<User> list = new ArrayList<>();
        list.add(user);
        User user1 = new User();
        user1.setId(2);
        list.add(user1);
        return list;
    }
}

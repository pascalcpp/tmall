package com.xpcf.tmall.web;

import com.xpcf.tmall.pojo.User;
import com.xpcf.tmall.service.UserService;
import com.xpcf.tmall.utils.Page4Navigator;
import netscape.security.UserTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 13:42
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page4Navigator<User> list
            (@RequestParam(value = "start",defaultValue = "0") Integer start,
             @RequestParam(value = "size",defaultValue = "5") Integer size) throws Exception{
        start = start>0?start:0;
        Page4Navigator page4Navigator = userService.list(start,size,5);
        return page4Navigator;


    }
}

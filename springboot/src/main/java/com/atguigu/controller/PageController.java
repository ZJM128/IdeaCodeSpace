package com.atguigu.controller;

import com.atguigu.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
//http://localhost:8080/abc/23?yy=zhangsan&name=wangwu
    @GetMapping("/abc/{xx}")
    public User getInfo (String name, @PathVariable("xx")String value, @RequestParam("yy")String y1){
        System.out.println(name+"---"+value+"---"+y1);
        User user = new User() ;
        user.setAge(20);
        user.setName("zhangsan");
        return user ;
    }


}

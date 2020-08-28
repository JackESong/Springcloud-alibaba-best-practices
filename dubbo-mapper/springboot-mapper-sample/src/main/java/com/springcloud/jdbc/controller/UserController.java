package com.springcloud.jdbc.controller;

import com.github.pagehelper.PageInfo;
import com.springcloud.jdbc.po.User;
import com.springcloud.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object list() {
        return userService.list();
    }


    /**
     * 跳转到应用列表页面
     * @param pageNo 要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<User> list(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize) {
        PageInfo<User> page = userService.getAllOrderPresentForPage(pageNo,pageSize);
        return  page;
    }

    @GetMapping("/add")
    public void add() {
        for (long i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setCity("深圳");
            user.setName("李四");
            userService.add(user);
        }
    }



}

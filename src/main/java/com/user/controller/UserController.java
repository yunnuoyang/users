package com.user.controller;

import com.user.pojo.Users;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{path}")
    public String pathParam(@PathVariable String path) {
        return path;
    }

    @RequestMapping("userlist")
    private ModelAndView getUsersList() {
        List<Users> usersList = userService.getUsersList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userlist", usersList);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping("del")
    private String del(Users users) {
        userService.delUserByID(users);
        return "redirect:userlist";
    }

    @RequestMapping("modify")
    private ModelAndView modifyUserByID(Users users) {
        //获取当前的修改的用户的信息
        Users user = userService.getCurUser(users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("curUser", user);
        modelAndView.setViewName("modiyUser");
        return modelAndView;
    }
    @RequestMapping("doupdate")
    private  String doupdate(Users users){
        userService.modifyUserByID(users);
        return "redirect:userlist";
    }

    @RequestMapping("insert")
    private  String insert(){
        Users users=new Users();
        users.setUsername("张三");
        users.setPassword("6666");
        userService.insert(users);
        return "redirect:userlist";
    }
}

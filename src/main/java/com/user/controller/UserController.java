package com.user.controller;

import com.user.pojo.Users;
import com.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
//    Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{path}")
    public String pathParam(@PathVariable String path) {
        return path;
    }

    @RequestMapping("userlist")
    public ModelAndView getUsersList() {
        List<Users> usersList = userService.getUsersList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userlist", usersList);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping("del")
//    BindingResult bindingResult
    public String del(Users users) {
        userService.delUserByID(users);
        return "redirect:userlist";
    }

    @RequestMapping("modify")
    public ModelAndView modifyUserByID(Users users) {
        //获取当前的修改的用户的信息
        Users user = userService.getCurUser(users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("curUser", user);
        modelAndView.setViewName("modiyUser");
        return modelAndView;
    }

    @RequestMapping("doupdate")
    public String doupdate(@Validated Users users, BindingResult br) {
        userService.modifyUserByID(users);
        return "redirect:userlist";
    }

    @RequestMapping("insert")
    public String insert(Users users) {
        userService.insert(users);
        return "redirect:userlist";
    }
}

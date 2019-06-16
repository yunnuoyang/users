package com.user.controller;

import com.user.pojo.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private  UserController userController;
    @Before
    public void setUp() throws Exception {
        this.mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    public void TestAdd(){
        Users users=new Users();
        users.setUsername("张三");
        users.setPassword("1234");
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/insert").param("username",users.getUsername()
            ).param("password",users.getPassword())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() throws Exception {
    }
}
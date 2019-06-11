package com.user.service;

import com.user.pojo.Users;

import java.util.List;

public interface UserService {
    List<Users> getUsersList();

    void delUserByID(Users users);

    void modifyUserByID(Users users) throws Exception;

    Users getCurUser(Users users);

    void insert(Users users);
}

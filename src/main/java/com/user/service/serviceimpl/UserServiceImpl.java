package com.user.service.serviceimpl;

import com.user.mapper.UsersMapper;
import com.user.pojo.Users;
import com.user.pojo.UsersExample;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getUsersList() {
        return usersMapper.selectByExample(null);
    }

    @Override
    public void delUserByID(Users users) {
        usersMapper.deleteByPrimaryKey(users.getId());
    }

    @Override
    public void modifyUserByID(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public Users getCurUser(Users users) {
        Users users1 = usersMapper.selectByPrimaryKey(users.getId());
        return users1;
    }

    @Override
    public void insert(Users users) {
        usersMapper.insertSelective(users);
    }
}
package com.user.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Users {
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String username;
    @Size(max = 10,message = "长度不能超过十个字节")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
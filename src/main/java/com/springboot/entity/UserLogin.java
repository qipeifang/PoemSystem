package com.springboot.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLogin {
    @NotNull(message = "邮箱账号不能为空")
    private String email;

    @NotNull(message = "密码不能为空")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


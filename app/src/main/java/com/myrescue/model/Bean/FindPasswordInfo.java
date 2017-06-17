package com.myrescue.model.Bean;

/**
 * 忘记密码 注册页面
 * Created by Men on 2017/6/8.
 */

public class FindPasswordInfo {
    private int account;
    private int code;
    private int password;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}

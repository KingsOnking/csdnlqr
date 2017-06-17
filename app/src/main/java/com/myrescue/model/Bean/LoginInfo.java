package com.myrescue.model.Bean;

/**
 * Created by Men on 2017/5/11.
 */

public class LoginInfo {
    private int account;
    private String password;

    public LoginInfo(int account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.myrescue.model.Bean;

import java.io.File;

/**
 * Created by Men on 2017/5/11.
 */

public class DriverUserBean {
    private Long account;
    private String password;
    private int code;
    private String user_name;
    private String number;
    private File idcard_1;
    private File idcard_2;
    private File license_1;
    private File license_2;
    private int user_type;
    public DriverUserBean(Long account, String password, int code, String user_name, String number, File idcard_1, File idcard_2, File license_1, File license_2, int user_type) {
        this.account = account;
        this.password = password;
        this.code = code;
        this.user_name = user_name;
        this.number = number;
        this.idcard_1 = idcard_1;
        this.idcard_2 = idcard_2;
        this.license_1 = license_1;
        this.license_2 = license_2;
        this.user_type = user_type;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public File getIdcard_1() {
        return idcard_1;
    }

    public void setIdcard_1(File idcard_1) {
        this.idcard_1 = idcard_1;
    }

    public File getIdcard_2() {
        return idcard_2;
    }

    public void setIdcard_2(File idcard_2) {
        this.idcard_2 = idcard_2;
    }

    public File getLicense_1() {
        return license_1;
    }

    public void setLicense_1(File license_1) {
        this.license_1 = license_1;
    }

    public File getLicense_2() {
        return license_2;
    }

    public void setLicense_2(File license_2) {
        this.license_2 = license_2;
    }

    public int getUuser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}

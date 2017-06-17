package com.myrescue.model.Bean;

/**
 * {
 "data": {
 "token": "a84180712c63b1d7cde91c33e3035ad1"
 },
 "code": 2000,
 "message": "success"
 }
 * Created by Men on 2017/4/24.
 */
public class ResponseInfo {

    private String code;
    private String data;
    private String message;
    public String getMessage(){return message;}
    public void setMessage(String message){this.message=message;}
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}

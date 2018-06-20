package com.beidou.domain;

/**
 * Created by lewis on 2018/6/13.
 */

public class UserBean {


    /**
     * password : 7c4a8d09ca3762af61e59520943dc26494f8941b
     * address : 1J6qFYJ2DhWiCrnGoYMzAdYp2qjG8AqxrqhJSS
     * logName : 13213243717
     * nickName : 小王
     * id : 7345
     * error : 0
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String password;
    private String address;
    private String logName;
    private String nickName;
    private int id;
    private int error;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}

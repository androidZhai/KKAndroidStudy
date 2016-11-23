package com.kkandroidstudy.bean;

/**
 * Created by shiyan on 2016/11/21.
 */

public class UserBean {
    private String userName;
    private String userPwd;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", age=" + age +
                '}';
    }
}

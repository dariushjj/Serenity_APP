package com.serenity.dao;

import android.content.ContentValues;

import com.serenity.dao.impl.UserDaoImpl;
import com.serenity.model.User;

import org.litepal.LitePal;

import java.util.List;

public class UserDao implements UserDaoImpl {
    public User getCurrentUser(){
        return LitePal.where("isSignIn = ?", "true").findFirst(User.class);
    }

    /**
     * 登录功能
     * @param name 账户
     * @param password 密码
     * @return 登录成功返回ture，同时更新数据库相关登录
     */
    public boolean signIn(String name, String password){
        boolean flag = false;

        List<User> users = LitePal.findAll(User.class);
        for (User u : users){
            if (u.getName().equals(name) && u.getPassword().equals(password)){
                ContentValues contentValues = new ContentValues();
                contentValues.put("isSignIn", "true");
                LitePal.update(User.class, contentValues, u.getId());
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isRegister(String name){
        boolean flag = false;

        List<User> users = LitePal.findAll(User.class);
        for (User u : users){
            if (u.getName().equals(name)){
                flag = true;
                break;
            }
        }

        return flag;
    }

    public void register(String name, String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.save();
    }

}

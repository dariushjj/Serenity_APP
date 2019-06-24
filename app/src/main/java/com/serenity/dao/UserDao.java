package com.serenity.dao;

import com.serenity.model.User;

import org.litepal.LitePal;

import java.util.List;

public class UserDao {

    public boolean isSignIn(String name, String password){
        boolean flag = false;

        List<User> users = LitePal.findAll(User.class);
        for (User u : users){
            if (u.getName().equals(name) && u.getPassword().equals(password)){
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

package com.serenity.dao.impl;

import com.serenity.model.User;

public interface UserDaoImpl {
    User getCurrentUser();

    boolean signIn(String name, String password);

    boolean isRegister(String name);

    void register(String name, String password);
}

package com.toeat.api.service;

import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.model.User;

public interface UserService {
    User signIn(String phone, String password) throws EtAuthException;
    User signUp(String phone, String name, String password, String confirm) throws EtAuthException;
}

package com.toeat.api.service;

import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.model.Client;

public interface ClientService {
    Client signIn(String phone, String password) throws EtAuthException;
    Client signUp(String phone, String name, String password, String confirm) throws EtAuthException;
}

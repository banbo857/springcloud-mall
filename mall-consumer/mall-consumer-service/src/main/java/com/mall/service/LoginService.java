package com.mall.service;


import com.mall.pojo.Consumer;

public interface LoginService {

    Consumer login(String account, String password);

    Integer register(String account, String password, String nickName, String phone);

    Integer checkAccount(String account);
}

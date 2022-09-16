package com.mall.service;



public interface LoginService {

    Integer login(String account, String password);

    Integer register(String account, String password, String nickName, String phone);

    Integer checkAccount(String account);
}

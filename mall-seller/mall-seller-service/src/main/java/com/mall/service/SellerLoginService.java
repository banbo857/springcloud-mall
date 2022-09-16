package com.mall.service;


import com.mall.pojo.Seller;

public interface SellerLoginService {

    /**
     * 商家登录
     */
    Seller login(String account, String password);

    /**
     * 商家注册
     */
    Integer register(String account, String password, String storeName, String storeIntroduce);

    /**
     * 注册验证
     */
    Integer checkAccount(String account, String storeName);
}

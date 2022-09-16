package com.mall.service;

import java.util.Map;

public interface CartService {
    boolean addCart(String goodsId);

    Map<String,Integer> getCart();

    boolean deleteCart(String goodsId);
}

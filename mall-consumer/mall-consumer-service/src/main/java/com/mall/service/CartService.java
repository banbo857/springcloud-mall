package com.mall.service;

import java.util.Map;

public interface CartService {
    boolean addCart(String goodsId, String JSessionId);

    Map<String, Integer> getCart(String JSessionId);

    boolean deleteCart(String goodsId, String JSessionId);
}

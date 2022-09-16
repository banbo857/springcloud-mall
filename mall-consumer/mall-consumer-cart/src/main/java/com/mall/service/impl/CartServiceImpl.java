package com.mall.service.impl;

import com.mall.service.CartService;
import com.mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisUtil redisUtil;

    private static final String CART_KEY = "cart:";


    @Override
    public boolean addCart(String goodsId) {
        Map<String,Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + "1");
        if (cart == null) {
            cart = new HashMap<>();
            cart.put(goodsId,1);
        }else {
            if (cart.containsKey(goodsId)) {
                cart.put(goodsId,cart.get(goodsId) + 1);
            }else {
                cart.put(goodsId,1);
            }
        }
        return redisUtil.set(CART_KEY + "1",cart);
    }

    @Override
    public Map<String, Integer> getCart() {
        Map<String,Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + "1");
        if (cart == null) {
            cart = new HashMap<>();
        }
        return cart;
    }

    @Override
    public boolean deleteCart(String goodsId) {
        Map<String,Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + "1");
        if (cart == null) {
            cart = new HashMap<>();
        }else {
            if (cart.containsKey(goodsId)) {
                cart.remove(goodsId);
            }
        }
        return redisUtil.set(CART_KEY + "1",cart);
    }
}

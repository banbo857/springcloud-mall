package com.mall.service.impl;

import com.mall.pojo.Consumer;
import com.mall.service.CartService;
import com.mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisUtil redisUtil;

    private static final String CART_KEY = "cart:";
    private final static String SESSION_KEY = "consumer:session:";


    @Override
    public boolean addCart(String goodsId, String JSessionId) {
        Consumer consumer = (Consumer) redisUtil.get(SESSION_KEY + JSessionId);
        Map<String, Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + consumer.getConsumerId());
        if (cart == null) {
            cart = new HashMap<>();
            cart.put(goodsId, 1);
        } else {
            if (cart.containsKey(goodsId)) {
                cart.put(goodsId, cart.get(goodsId) + 1);
            } else {
                cart.put(goodsId, 1);
            }
        }
        return redisUtil.set(CART_KEY + consumer.getConsumerId(), cart);
    }

    @Override
    public Map<String, Integer> getCart(String JSessionId) {
        Consumer consumer = (Consumer) redisUtil.get(SESSION_KEY + JSessionId);
        Map<String, Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + consumer.getConsumerId());
        if (cart == null) {
            cart = new HashMap<>();
        }
        return cart;
    }

    @Override
    public boolean deleteCart(String goodsId, String JSessionId) {
        Consumer consumer = (Consumer) redisUtil.get(SESSION_KEY + JSessionId);
        Map<String, Integer> cart = (Map<String, Integer>) redisUtil.get(CART_KEY + consumer.getConsumerId());
        if (cart == null) {
            cart = new HashMap<>();
        } else {
            if (cart.containsKey(goodsId)) {
                cart.remove(goodsId);
            }
        }
        return redisUtil.set(CART_KEY + consumer.getConsumerId(), cart);
    }
}

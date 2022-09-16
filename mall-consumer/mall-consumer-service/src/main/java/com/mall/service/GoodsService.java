package com.mall.service;

import com.mall.pojo.Goods;

import java.util.List;

public interface GoodsService {


    List<Goods> goodsList();

    List<Goods> search(String title);

    Goods getGoodsById(String goodsId);
}

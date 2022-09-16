package com.mall.service.Impl;

import com.mall.dao.GoodsDao;
import com.mall.pojo.Goods;
import com.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> goodsList() {

        return goodsDao.goodsList();
    }

    @Override
    public List<Goods> search(String title) {
        return goodsDao.search(title);
    }

    @Override
    public Goods getGoodsById(String goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }
}

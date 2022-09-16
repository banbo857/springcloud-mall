package com.mall.dao;

import com.mall.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerGoodsDao {

    List<Goods> goodsList(@Param("sellerId") Integer sellerId);

    List<Goods> search(@Param("title") String title, @Param("sellerId") Integer sellerId);

    Goods getGoodsById(@Param("goodsId") String goodsId);

    int addGoods(Goods goods);

    int updateGoods(Goods goods);

    int deleteGoods(@Param("goodsId") String goodsId);

    int upStatus(@Param("goodsId") String goodsId, @Param("status") Integer status);


}

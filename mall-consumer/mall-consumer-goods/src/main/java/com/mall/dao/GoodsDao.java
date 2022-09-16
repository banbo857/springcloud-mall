package com.mall.dao;

import com.mall.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    List<Goods> goodsList();

    List<Goods> search(@Param("title") String title);

    Goods getGoodsById(@Param("goodsId") String goodsId);
}

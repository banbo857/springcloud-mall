package com.mall.service;

import com.mall.pojo.Goods;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SellerGoodsService {

    /**
     * 获取商家全部商品
     */
    List<Goods> goodsList(HttpServletRequest request);

    /**
     * 查找商品
     */
    List<Goods> search(String title);

    /**
     * 根据id查询商品
     */
    Goods getGoodsById(String goodsId);

    /**
     * 添加商品
     */
    int addGoods(String title, String introduce, String price, String stock, String img, String type, MultipartFile file);

    /**
     * 修改商品
     */
    int updateGoods(String goodsId, String title, String introduce, String price, String stock, String img, String type);

    /**
     * 删除商品
     */
    int deleteGoods(String goodsId);

    /**
     * 上架商品\下架商品
     */
    int upStatus(String goodsId, Integer status);


    byte[] getImg(String path);

}

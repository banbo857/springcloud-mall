package com.mall.service.Impl;

import com.mall.dao.SellerGoodsDao;
import com.mall.pojo.Goods;
import com.mall.pojo.Seller;
import com.mall.service.SellerGoodsService;
import com.mall.utils.FastdfsUtils;
import com.mall.utils.RedisUtil;
import com.mall.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GoodsServiceImpl implements SellerGoodsService {

    @Autowired
    private SellerGoodsDao goodsDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FastdfsUtils fastdfsUtils;

    private static final String SESSION_KEY = "seller:session:";
    /**
     * 获取商家全部商品
     */
    @Override
    public List<Goods> goodsList(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String JSESSIONID = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                JSESSIONID = cookie.getValue();
            }
        }
        Seller seller = (Seller) redisUtil.get(SESSION_KEY + JSESSIONID);
        Integer sellerId = seller.getSellerId();
        return goodsDao.goodsList(sellerId);
    }

    /**
     * 查找商品
     * @param title
     */
    @Override
    public List<Goods> search(String title) {
        Integer sellerId = 1;
        return goodsDao.search(title,sellerId);
    }

    /**
     * 根据id查询商品
     *
     * @param goodsId
     */
    @Override
    public Goods getGoodsById(String goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    /**
     * 添加商品
     *
     */
    @Override
    public int addGoods(String title, String introduce, String price, String stock, String img, String type, MultipartFile file) {
        try {
            String images = fastdfsUtils.upload(file);
            Integer sellerId = 1;
            Goods goods = new Goods();
            goods.setTitle(title)
                    .setIntroduce(introduce)
                    .setPrice(Double.parseDouble(price))
                    .setStock(Integer.valueOf(stock))
                    .setImages(images)
                    .setTypeId(Integer.valueOf(type))
                    .setSellerId(sellerId)
                    .setStatus(0);
            return goodsDao.addGoods(goods);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 修改商品
     *
     */
    @Override
    public int updateGoods(String goodsId, String title, String introduce, String price, String stock, String img, String type) {
        Goods goods = goodsDao.getGoodsById(goodsId);
        goods.setTitle(title)
                .setIntroduce(introduce)
                .setPrice(Double.parseDouble(price))
                .setStock(Integer.valueOf(stock))
                .setImages(img)
                .setTypeId(Integer.valueOf(type));
        return goodsDao.updateGoods(goods);
    }

    /**
     * 删除商品
     *
     * @param goodsId
     */
    @Override
    public int deleteGoods(String goodsId) {
        return goodsDao.deleteGoods(goodsId);
    }

    /**
     * 上架商品\下架商品
     *
     * @param goodsId
     */
    @Override
    public int upStatus(String goodsId, Integer status) {
        return goodsDao.upStatus(goodsId,status);
    }


    @Override
    public byte[] getImg(String path) {
        try {
            return fastdfsUtils.download(path);
        } catch (Exception e) {
            return null;
        }
    }
}

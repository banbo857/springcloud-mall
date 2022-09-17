package com.mall.service.Impl;

import com.mall.dao.SellerOrderDao;
import com.mall.pojo.*;
import com.mall.service.SellerOrderService;
import com.mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements SellerOrderService {

    @Autowired
    private SellerOrderDao orderDao;
    @Autowired
    private RedisUtil redisUtil;

    private final static String SESSION_KEY = "seller:session:";

    /**
     * 获取商家全部订单
     */
    @Override
    public List<GoodsOrder> getAllOrder(String sessionId) {
        Seller seller = (Seller) redisUtil.get(SESSION_KEY + sessionId);
        return orderDao.getAllOrder(seller.getSellerId());
    }

    /**
     * 查询订单
     *
     * @param keyWord
     */
    @Override
    public List<GoodsOrder> searchOrder(String keyWord, String sessionId) {
        Seller seller = (Seller) redisUtil.get(SESSION_KEY + sessionId);
        List<GoodsOrder> goodsOrders = orderDao.getAllOrder(seller.getSellerId());
//        List<GoodsOrder> res = new ArrayList<>();
//        for (GoodsOrder goodsOrder : goodsOrders) {
//            if (goodsOrder.getNumber().equals(keyWord)) {
//                res.add(goodsOrder);
//                return res;
//            }
//        }
//        List<Goods> goodsList = orderDao.getGoodsBySellerId(sellerId);
//        for (Goods goods : goodsList) {
//            if (goods.getTitle().contains(keyWord)) {
//                for (GoodsOrder goodsOrder : goodsOrders) {
//                    if (goodsOrder.getGoodsId().equals(goods.getGoodsId())) {
//                        res.add(goodsOrder);
//                    }
//                }
//            }
//        }
        return goodsOrders;
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     */
    @Override
    public int updateStatusByOrderId(String orderId, String status) {
        return orderDao.updateStatusByOrderId(orderId, status);
    }

    /**
     * 更新发货时间 物流信息
     *
     * @param orderId
     * @param deliverTime
     * @param logisticsId
     */
    @Override
    public int updateDeliverTimeByOrderId(String orderId, String deliverTime, Integer logisticsId) {
        return orderDao.updateDeliverTimeByOrderId(orderId, deliverTime, logisticsId);
    }

    /**
     * 新增物流信息
     */
    @Override
    public int addLogistics(String company, String number) {
        Logistics logistics = new Logistics();
        logistics.setCompany(company)
                .setNumber(number);
        orderDao.addLogistics(logistics);
        return logistics.getLogisticsId();
    }
}

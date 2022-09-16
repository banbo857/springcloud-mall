package com.mall.service.Impl;

import com.alibaba.fastjson.JSON;
import com.mall.dao.OrderDao;
import com.mall.pojo.Evaluate;
import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;
import com.mall.pojo.OrderItem;
import com.mall.service.FeignGoodsService;
import com.mall.service.OrderService;
import com.mall.utils.DateUtils;
import com.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private FeignGoodsService feignGoodsService;

    /**
     * 获取全部订单
     */
    @Override
    public List<GoodsOrder> getAllOrder(Integer consumerId) {
        return orderDao.getAllOrder(consumerId);
    }

    /**
     * 查找订单
     */
    @Override
    public List<GoodsOrder> searchOrder(String keyWord) {
        Integer consumerId = 1;

        List<GoodsOrder> allOrder = orderDao.getAllOrder(consumerId);//获取全部订单
//        List<Goods> goodsList = orderDao.getGoodsByConsumerId(consumerId);//获取全部订单中的商品
//        List<GoodsOrder> res = new ArrayList<>();
//        for (GoodsOrder goodsOrder : allOrder) {
//            if (goodsOrder.getNumber().equals(keyWord)) {//订单号匹配
//                res.add(goodsOrder);
//                return res;
//            }
//        }
//        for (Goods goods : goodsList) {
//            if (goods.getTitle().contains(keyWord)) {
//                for (GoodsOrder goodsOrder : allOrder) {
//                    if (goodsOrder.getGoodsId().equals(goods.getGoodsId())) {
//                        res.add(goodsOrder);
//                    }
//                }
//            }
//        }
        return allOrder;
    }

    /**
     * 更新订单状态
     */
    @Override
    public int updateStatusByOrderId(String orderId, int status) {
        return orderDao.updateStatusByOrderId(orderId, status);
    }

    /**
     * 更新订单支付时间
     */
    @Override
    public int updatePayTimeByOrderId(String orderId) {
        return orderDao.updatePayTimeByOrderId(orderId, DateUtils.fromDateH());
    }

    /**
     * 创建订单
     */
    @Override
    public int createOrder(String goodsIds, String goodsNums, String addressId) {
        //UNDO 商家不同拆分订单
        String[] goodsId = goodsIds.split(",");
        String[] goodsNum = goodsNums.split(",");
        List<Goods> goodsList = new ArrayList<>();
        double total = 0;
        for (int i = 0; i < goodsId.length; i++) {
            Map<String,Object> map = feignGoodsService.getGoodsById(goodsId[i]);
            //fastjson转bean
            Goods goods = JSON.parseObject(JSON.toJSONString(map.get("goods")), Goods.class);
            if (goods == null) {
                return -1;
            }
            if (goods.getStock() < Integer.parseInt(goodsNum[i])) {
                return -2;
            }
            if (goodsNum[i].equals("")) {
                return -3;
            }
            goodsList.add(goods);
            total += goods.getPrice() * Integer.parseInt(goodsNum[i]);
        }
        Calendar calendar = Calendar.getInstance();
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setCreateTime(DateUtils.fromDateH())
                .setNumber(calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1) + ""
                        + calendar.get(Calendar.DAY_OF_MONTH) + 1 + "" + UUID.randomUUID().toString().substring(0, 8))
                .setAddressId(Integer.valueOf(addressId))
                .setConsumerId(1)
                .setStatus(1)
                .setSellerId(goodsList.get(0).getSellerId())
                .setPrice(total);
        List<OrderItem> orderItems = new ArrayList<>();
        if (orderDao.createOrder(goodsOrder) > 0) {
            for (int i = 0; i < goodsId.length; i++) {
                orderItems.add(new OrderItem().setOrderId(goodsOrder.getOrderId())
                                            .setGoodsId(Integer.valueOf(goodsId[i]))
                                            .setNum(Integer.valueOf(goodsNum[i])));
            }
        }
        return orderDao.addItem(orderItems);

    }

    /**
     * 评价
     */
    @Override
    public int evaluate(String goodsId, String content, String evaluateLevel) {
        Evaluate evaluate = new Evaluate();
        evaluate.setGoodsId(Integer.valueOf(goodsId))
                .setContent(content)
                .setEvaluateLevel(Integer.valueOf(evaluateLevel))
                .setCreateTime(DateUtils.fromDateH())
                .setConsumerId(1);
        return orderDao.evaluate(evaluate);
    }
}

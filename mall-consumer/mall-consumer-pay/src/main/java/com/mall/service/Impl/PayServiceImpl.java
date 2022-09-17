package com.mall.service.Impl;

import com.mall.dao.PayDao;
import com.mall.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    @Override
    public boolean pay() {
        //支付模块未写 直接返回true
        return true;
    }
}
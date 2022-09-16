package com.mall.service.Impl;

import com.mall.dao.PayDao;
import com.mall.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    @Override
    public boolean pay() {
        
        return true;
    }
}
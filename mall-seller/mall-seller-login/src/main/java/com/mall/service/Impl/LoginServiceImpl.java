package com.mall.service.Impl;

import com.mall.dao.SellerLoginDao;
import com.mall.pojo.Seller;
import com.mall.service.SellerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements SellerLoginService {

    @Autowired
    private SellerLoginDao sellerLoginDao;

    @Override
    public Seller login(String account, String password) {
        return sellerLoginDao.login(account, password);
    }

    @Override
    public Integer register(String account, String password, String storeName, String storeIntroduce) {
        if (sellerLoginDao.checkAccount(account, storeName) > 0) {
            return -1;
        }
        return sellerLoginDao.register(account, password, storeName, storeIntroduce);
    }

    @Override
    public Integer checkAccount(String account, String storeName) {
        return sellerLoginDao.checkAccount(account, storeName);
    }
}

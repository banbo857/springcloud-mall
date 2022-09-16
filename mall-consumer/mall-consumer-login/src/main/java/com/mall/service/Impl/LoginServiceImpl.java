package com.mall.service.Impl;

import com.mall.dao.LoginDao;
import com.mall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Integer login(String account, String password) {
        return loginDao.login(account, password);
    }

    @Override
    public Integer register(String account, String password, String nickName, String phone) {
        if (loginDao.checkAccount(account) > 0) {
            return -1;
        }
        return loginDao.register(account, password, nickName, phone);
    }

    @Override
    public Integer checkAccount(String account) {
        return loginDao.checkAccount(account);
    }
}

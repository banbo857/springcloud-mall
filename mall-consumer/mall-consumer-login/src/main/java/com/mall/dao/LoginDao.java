package com.mall.dao;

import com.mall.pojo.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {
    Consumer login(@Param("account") String account, @Param("password") String password);

    Integer register(@Param("account") String account, @Param("password") String password, @Param("nickName") String nickName, @Param("phone") String phone);

    Integer checkAccount(@Param("account") String account);
}

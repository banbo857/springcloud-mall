package com.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {
    Integer login(@Param("account") String account, @Param("password") String password);

    Integer register(@Param("account") String account, @Param("password") String password, @Param("nickName") String nickName, @Param("phone") String phone);

    Integer checkAccount(@Param("account") String account);
}

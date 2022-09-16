package com.mall.dao;

import com.mall.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SellerLoginDao {
    Seller login(@Param("account") String account, @Param("password") String password);

    Integer register(@Param("account") String account, @Param("password") String password, @Param("storeName") String storeName, @Param("storeIntroduce") String storeIntroduce);

    Integer checkAccount(@Param("account") String account, @Param("storeName") String storeName);
}

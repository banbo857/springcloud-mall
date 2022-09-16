package com.mall.factory;

import com.mall.service.SellerFeignGoodsService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class GoodsServiceFallBackFactory implements FallbackFactory<SellerFeignGoodsService> {
    @Override
    public SellerFeignGoodsService create(Throwable throwable) {
        return new SellerFeignGoodsService() {
            @Override
            public Result goodsList() {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result search(String title) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result getGoodsById(String goodsId) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result addGoods(String title, String introduce, String price, String stock, String img, String type, MultipartFile file) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result deleteGoods(String goodsId) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result updateGoods(String goodsId, String title, String introduce, String price, String stock, String img, String type) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result upStatus(String goodsId, String status) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }

            @Override
            public Result getImg(String path) {
                log.error("商品服务异常", throwable);
                return Result.error("商品服务异常");
            }
        };
    }
}

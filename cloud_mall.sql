/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : cloud_mall

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 17/09/2022 20:56:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`
(
    `consumer_id` int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT 'consumer_id',
    `account`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
    `password`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
    `nick_name`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
    PRIMARY KEY (`consumer_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------

-- ----------------------------
-- Table structure for consumer_address
-- ----------------------------
DROP TABLE IF EXISTS `consumer_address`;
CREATE TABLE `consumer_address`
(
    `address_id`  int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT 'address_id',
    `name`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
    `address`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
    `consumer_id` int(0)                                                       NULL DEFAULT NULL COMMENT '消费者id',
    PRIMARY KEY (`address_id`) USING BTREE,
    INDEX `消费者id` (`consumer_id`) USING BTREE,
    CONSTRAINT `消费者id` FOREIGN KEY (`consumer_id`) REFERENCES `consumer` (`consumer_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer_address
-- ----------------------------

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`
(
    `evaluate_id`    int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT 'evaluate_id',
    `create_time`    datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `evaluate_level` int(0)                                                        NULL DEFAULT NULL COMMENT '等级',
    `content`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
    `consumer_id`    int(0)                                                        NULL DEFAULT NULL COMMENT '消费者id',
    `goods_id`       int(0)                                                        NULL DEFAULT NULL COMMENT '商品id',
    `reply_id`       int(0)                                                        NULL DEFAULT NULL COMMENT '回复id',
    PRIMARY KEY (`evaluate_id`) USING BTREE,
    INDEX `消费者id2` (`consumer_id`) USING BTREE,
    INDEX `商品id` (`goods_id`) USING BTREE,
    INDEX `回复id` (`reply_id`) USING BTREE,
    CONSTRAINT `商品id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `回复id` FOREIGN KEY (`reply_id`) REFERENCES `evaluate_reply` (`reply_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `消费者id2` FOREIGN KEY (`consumer_id`) REFERENCES `consumer` (`consumer_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluate
-- ----------------------------

-- ----------------------------
-- Table structure for evaluate_reply
-- ----------------------------
DROP TABLE IF EXISTS `evaluate_reply`;
CREATE TABLE `evaluate_reply`
(
    `reply_id`    int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT 'reply_id',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
    `evaluate_id` int(0)                                                        NULL DEFAULT NULL COMMENT '评价id',
    PRIMARY KEY (`reply_id`) USING BTREE,
    INDEX `评价id` (`evaluate_id`) USING BTREE,
    CONSTRAINT `评价id` FOREIGN KEY (`evaluate_id`) REFERENCES `evaluate` (`evaluate_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluate_reply
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `goods_id`  int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT 'goods_id',
    `title`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
    `price`     decimal(10, 2)                                                NULL DEFAULT NULL COMMENT '价格',
    `stock`     int(0)                                                        NULL DEFAULT NULL COMMENT '库存',
    `images`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '库存',
    `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '介绍',
    `status`    int(0)                                                        NULL DEFAULT NULL COMMENT '状态',
    `type_id`   int(0)                                                        NULL DEFAULT NULL COMMENT '分类id',
    `seller_id` int(0)                                                        NULL DEFAULT NULL COMMENT '商家id',
    PRIMARY KEY (`goods_id`) USING BTREE,
    INDEX `分类` (`type_id`) USING BTREE,
    INDEX `商家` (`seller_id`) USING BTREE,
    CONSTRAINT `分类` FOREIGN KEY (`type_id`) REFERENCES `goods_type` (`type_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `商家` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`seller_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order`
(
    `order_id`     int(0)                                                       NOT NULL AUTO_INCREMENT,
    `number`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
    `create_time`  datetime(0)                                                  NULL DEFAULT NULL COMMENT '创建时间',
    `pay_time`     datetime(0)                                                  NULL DEFAULT NULL COMMENT '付款时间',
    `deliver_time` datetime(0)                                                  NULL DEFAULT NULL COMMENT '发货时间',
    `deal_time`    datetime(0)                                                  NULL DEFAULT NULL COMMENT '成交时间',
    `price`        decimal(10, 2)                                               NULL DEFAULT NULL COMMENT '价格',
    `status`       int(0)                                                       NULL DEFAULT NULL COMMENT '状态',
    `seller_id`    int(0)                                                       NULL DEFAULT NULL COMMENT '商家id',
    `logistics_id` int(0)                                                       NULL DEFAULT NULL COMMENT '物流id',
    `consumer_id`  int(0)                                                       NULL DEFAULT NULL COMMENT '消费者id',
    `address_id`   int(0)                                                       NULL DEFAULT NULL COMMENT '收货地址id',
    PRIMARY KEY (`order_id`) USING BTREE,
    INDEX `收货地址` (`address_id`) USING BTREE,
    INDEX `消费者` (`consumer_id`) USING BTREE,
    INDEX `物流` (`logistics_id`) USING BTREE,
    INDEX `卖家` (`seller_id`) USING BTREE,
    CONSTRAINT `卖家` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`seller_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `收货地址` FOREIGN KEY (`address_id`) REFERENCES `consumer_address` (`address_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `消费者` FOREIGN KEY (`consumer_id`) REFERENCES `consumer` (`consumer_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `物流` FOREIGN KEY (`logistics_id`) REFERENCES `logistics` (`logistics_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_order
-- ----------------------------
INSERT INTO `goods_order`
VALUES (1, '20229161e312312', '2022-09-16 18:50:39', '2022-09-16 18:50:41', '2022-09-16 18:50:44',
        '2022-09-16 18:50:46', 10000.00, 5, NULL, NULL, NULL, NULL);
INSERT INTO `goods_order`
VALUES (2, 'd213', '2022-09-16 19:11:50', '2022-09-16 19:13:02', '2022-09-16 19:13:06', NULL, 5000.00, 4, NULL, NULL,
        NULL, NULL);
INSERT INTO `goods_order`
VALUES (7, '20229161e2eca62e', '2022-09-16 17:39:26', '2022-09-01 19:10:00', '2022-09-16 18:48:27',
        '2022-09-16 18:48:29', 15698.00, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`
(
    `type_id` int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT 'type_id',
    `type`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
    PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type`
VALUES (1, '手机');

-- ----------------------------
-- Table structure for logistics
-- ----------------------------
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics`
(
    `logistics_id` int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT 'logistics_id',
    `company`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流公司',
    `number`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流单号',
    PRIMARY KEY (`logistics_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logistics
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`
(
    `item_id`  int(0) NOT NULL AUTO_INCREMENT,
    `order_id` int(0) NULL DEFAULT NULL COMMENT '订单id',
    `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
    `num`      int(0) NULL DEFAULT NULL COMMENT '数量',
    PRIMARY KEY (`item_id`) USING BTREE,
    INDEX `order` (`order_id`) USING BTREE,
    INDEX `goods` (`goods_id`) USING BTREE,
    CONSTRAINT `goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `order` FOREIGN KEY (`order_id`) REFERENCES `goods_order` (`order_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`
(
    `seller_id`       int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT 'seller_id',
    `account`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '账号',
    `password`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '密码',
    `store_name`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '店铺名',
    `store_introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '店铺介绍',
    `balance`         decimal(10, 0)                                                NULL DEFAULT NULL COMMENT '余额',
    PRIMARY KEY (`seller_id`, `account`) USING BTREE,
    INDEX `seller_id` (`seller_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

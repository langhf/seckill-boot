USE seckill_boot;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id BIGINT NOT NULL COMMENT '物品ID',
    number  INT NOT NULL COMMENT '物品剩余数量',
    description VARCHAR(255) NOT NULL COMMENT '物品描述',
    start_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
    end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '物品创建时间',
    PRIMARY KEY (product_id)
) ENGINE InnoDB DEFAULT CHAR SET utf8mb4 COMMENT '物品表';

DROP TABLE IF EXISTS product_order;
CREATE TABLE product_order (
    product_id BIGINT NOT NULL COMMENT '物品ID',
    mobile CHAR(11) NOT NULL COMMENT '秒杀者手机号',
    status TINYINT  NOT NULL DEFAULT 0  COMMENT '订单状态 -1：无效， 0：成功， 1：已付款',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
    PRIMARY KEY (product_id, mobile), -- 联合主键可以防止同一用户秒杀多个相同物品
    key idx_create_time(create_time)
) ENGINE InnoDB DEFAULT CHAR SET utf8mb4 COMMENT '订单表';

INSERT INTO product (product_id, number, description, start_time, end_time) VALUES
    (10001, 100, '1元秒杀华为P30', '2019-06-26 18:00:00', '2019-06-27 18:00:00'),
    (10002, 100, '10元秒杀小米9', '2019-06-27 18:00:00', '2019-06-30 18:00:00'),
    (10003, 100, '5元秒杀华为荣耀8', '2019-06-30 18:00:00', '2019-07-03 18:00:00'),
    (10004, 100, '1元秒杀华为P30 Pro', '2019-06-27 18:00:00', '2019-06-30 18:00:00');

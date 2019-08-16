package cn.drelang.seckillboot.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Drelang on 2019/06/27 21:05
 */
@Data
public class ProductOrder {

    private long productId;

    private String mobile;

    private int status;

    private Date createTime;

    // 多对一
    private Product product;
}


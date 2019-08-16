package cn.drelang.seckillboot.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Drelang on 2019/06/27 20:59
 */
@Data
public class Product {
    private long productId;

    private int number;

    private String description;

    private Date startTime;

    private Date endTime;

    private Date createTime;
}


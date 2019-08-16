package cn.drelang.seckillboot.dto;

import cn.drelang.seckillboot.entity.Product;
import lombok.Data;

import java.util.Date;

/**
 * Created by Drelang on 2019/06/28 09:34
 */
@Data
public class Exposer {

    private boolean begin;  // 秒杀是否开始

    private String bcrypt; // 秒杀加密措施

    private Date now;   // 服务器当前时间

    private Product product;

}


package cn.drelang.seckillboot.dto;

import cn.drelang.seckillboot.entity.ProductOrder;
import lombok.Data;

/**
 * Created by Drelang on 2019/06/28 12:06
 */
@Data
public class ExecutionSeckill {

    private boolean success;    // 是否秒杀成功

    private String message; // 秒杀结果提醒消息

    private ProductOrder productOrder;  // 秒杀成功的订单
}


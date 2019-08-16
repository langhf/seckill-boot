package cn.drelang.seckillboot.service;

import cn.drelang.seckillboot.dto.ExecutionSeckill;
import cn.drelang.seckillboot.dto.Exposer;
import cn.drelang.seckillboot.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {

    /**
     * 查询所有物品
     * @return
     */
    List<Product> queryAll();

    /**
     * 根据Id查询物品
     * @param productId
     * @return
     */
    Product queryById(long productId);

    /**
     * 暴露秒杀路由地址
     * @param productId 秒杀物品id
     * @return
     */
    Exposer exportUrl(long productId);

    /**
     * 执行秒杀
     * @param productId 秒杀物品id
     * @param bcrypt 秒杀时间，以服务器时间为准
     * @return > 0 表示秒杀成功
     */
    ExecutionSeckill executeKill(long productId, String bcrypt, String mobile);
}

package cn.drelang.seckillboot.dao;

import cn.drelang.seckillboot.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductDao {
    /**
     * 查询所有的商品
     * @return
     */
    List<Product> queryAll();

    /**
     * 根据ID查询商品
     * @param productId
     * @return
     */
    Product queryById(@Param("productId") Long productId);

    /**
     * 减库存
     * @param productId
     * @param killTime 秒杀时间
     * @return 如果影响行数 > 1，表示更新的行数
     */
    int reduceNumber(@Param("productId") Long productId, @Param("killTime") Date killTime);
}

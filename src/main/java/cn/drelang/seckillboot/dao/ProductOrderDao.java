package cn.drelang.seckillboot.dao;

import cn.drelang.seckillboot.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;

public interface ProductOrderDao {
    /**
     * 插入一条新的记录
     * @param productId
     * @param mobile
     * @return 插入的行数
     */
    int insertOneLog(@Param("productId") long productId, @Param("mobile") String mobile);

    /**
     * 根据商品id和手机号查询订单记录
     * @param productId
     * @param mobile
     * @return
     */
    ProductOrder queryByIdWithProduct(@Param("productId") long productId, @Param("mobile") String mobile);
}

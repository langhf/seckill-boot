package cn.drelang.seckillboot.dao.cache;

import cn.drelang.seckillboot.dao.ProductDao;
import cn.drelang.seckillboot.entity.Product;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Drelang on 2019/06/29 12:41
 */
@Component
public class RedisDao {

    @Resource
    private RedisTemplate<byte[], Object> redisTemplate;

    @Resource
    private ProductDao productDao;

    private RuntimeSchema<Product> schema = RuntimeSchema.createFrom(Product.class);

    public Product get(long producId) {
        String key = "productId:" + producId;
        byte[] bytes = (byte[])redisTemplate.opsForValue().get(key.getBytes());
        if (bytes != null) {
            Product product = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, product, schema);
            return product;
        } else {
            Product product = productDao.queryById(producId);
            if (product == null) {
                throw new RuntimeException("秒杀活动不存在");
            }
            put(key, product);
            return product;
        }
    }

    public void put(String key, Product product) {
        byte[] bytes = ProtostuffIOUtil.toByteArray(product, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        int timeout = 1;
        redisTemplate.opsForValue().set(key.getBytes(), bytes, timeout, TimeUnit.HOURS);

    }
}


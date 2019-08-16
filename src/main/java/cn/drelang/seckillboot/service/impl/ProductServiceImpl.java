package cn.drelang.seckillboot.service.impl;

import cn.drelang.seckillboot.dao.ProductDao;
import cn.drelang.seckillboot.dao.ProductOrderDao;
import cn.drelang.seckillboot.dao.cache.RedisDao;
import cn.drelang.seckillboot.dto.ExecutionSeckill;
import cn.drelang.seckillboot.dto.Exposer;
import cn.drelang.seckillboot.entity.Product;
import cn.drelang.seckillboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Drelang on 2019/06/28 11:51
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisDao redisDao;


    @Override
    public List<Product> queryAll() {
        return productDao.queryAll();
    }

    @Override
    public Product queryById(long productId) {
        return redisDao.get(productId);
    }

    @Override
    public Exposer exportUrl(long productId) {
        Date now = new Date();
        Product product = productDao.queryById(productId);

        Exposer exposer = new Exposer();
        exposer.setProduct(product);
        exposer.setNow(new Date());
        if (now.getTime() < product.getStartTime().getTime()
                || now.getTime() > product.getEndTime().getTime()) {
            exposer.setBegin(false);    // 秒杀未开始或者已经结束
        } else {
            exposer.setBegin(true);
            String bcrypt = passwordEncoder.encode(String.valueOf(productId));
            exposer.setBcrypt(bcrypt);
        }
        return exposer;
    }

    @Override
    @Transactional
    public ExecutionSeckill executeKill(long productId, String bcrypt, String mobile) {
        Date now = new Date();
        ExecutionSeckill execution = new ExecutionSeckill();
        Product product = productDao.queryById(productId);
        if (now.before(product.getStartTime())) {
            execution.setSuccess(false);
            execution.setMessage("秒杀未开始");
            return execution;
        }
        if (now.after(product.getEndTime())) {
            execution.setSuccess(false);
            execution.setMessage("秒杀已经结束");
            return execution;
        }

        if (!passwordEncoder.matches(String.valueOf(productId), bcrypt)) {
            execution.setSuccess(false);
            execution.setMessage("秒杀地址有误");
            return execution;
        }
        int count = productOrderDao.insertOneLog(productId, mobile);
        if (count != 1) {
            execution.setSuccess(false);
            execution.setMessage("重复秒杀");
            return execution;
        }
        count = productDao.reduceNumber(productId, now);
        if (count !=1 ) {
            execution.setSuccess(false);
            execution.setMessage("秒杀失败");
            return execution;
        }

        execution.setSuccess(true);
        execution.setMessage("秒杀成功");
        execution.setProductOrder(productOrderDao.queryByIdWithProduct(productId, mobile));
        return execution;
    }
}


package cn.drelang.seckillboot.dao;

import cn.drelang.seckillboot.SeckillBootApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ProductDaoTest extends SeckillBootApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductDao productDao;

    @Test
    public void queryAll() {
        logger.info(productDao.queryAll() + "");
    }

    @Test
    public void queryById() {
        long id = 10001;
        logger.info(productDao.queryById(id) + "");
    }

    @Test
    public void reduceNumber() {
        long productId = 10001;
        Date killTime = new Date();
        logger.info(productDao.reduceNumber(productId, killTime) + "");

        long productId2 = 10002;
        logger.info(productDao.reduceNumber(productId2, killTime) + "");
    }
}
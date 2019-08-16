package cn.drelang.seckillboot.dao;

import cn.drelang.seckillboot.SeckillBootApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ProductOrderDaoTest extends SeckillBootApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductOrderDao productOrderDao;

    @Test
    public void insertOneLog() {
        long product_id = 10001;
        String mobile = "13156533607";
        logger.info(productOrderDao.insertOneLog(product_id, mobile) + "");
    }

    @Test
    public void queryByIdWithProduct() {
        long productId = 10001;
        String mobile = "13156533607";
        logger.info(productOrderDao.queryByIdWithProduct(productId, mobile) + "");
    }
}
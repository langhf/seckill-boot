package cn.drelang.seckillboot.service.impl;

import cn.drelang.seckillboot.SeckillBootApplicationTests;
import cn.drelang.seckillboot.service.ProductService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends SeckillBootApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Test
    public void queryAll() {
        logger.info(productService.queryAll()+"");
    }

    @Test
    public void queryById() {
        long productId = 10001;
        logger.info("result={}", productService.queryById(productId));
    }

    @Test
    public void exportUrl() {
        logger.info("result1={}", productService.exportUrl(10001));
        logger.info("result2={}", productService.exportUrl(10002));
        logger.info("result3={}", productService.exportUrl(10003));
        logger.info("result4={}", productService.exportUrl(10004));
    }

    @Test
    public void executeKill() {
        logger.info("result={}", productService.executeKill(10002, "$2a$10$C4VxBTnmk5OkVANsvVawKeC.tVdovqjwsOVhBl3xnSG/HPqnlBm8u", "13156533607"));
        logger.info("result={}", productService.executeKill(10004, "$2a$10$K3PkHBqmoV9Udk1G/FpYyO.08TsflXP5Az4lfSFtOVGqG2wI5Yayi", "13156533607"));

    }
}
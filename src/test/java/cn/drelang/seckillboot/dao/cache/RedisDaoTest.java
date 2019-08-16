package cn.drelang.seckillboot.dao.cache;

import cn.drelang.seckillboot.SeckillBootApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class RedisDaoTest extends SeckillBootApplicationTests {

    @Autowired
    private RedisDao redisDao;

    @Test
    public void test() {
        long id = 10001;
        log.info(redisDao.get(id) +"");
    }
}
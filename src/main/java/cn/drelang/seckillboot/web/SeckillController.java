package cn.drelang.seckillboot.web;

import cn.drelang.seckillboot.dto.ExecutionSeckill;
import cn.drelang.seckillboot.dto.ExecutionSeckillParam;
import cn.drelang.seckillboot.dto.Exposer;
import cn.drelang.seckillboot.entity.Product;
import cn.drelang.seckillboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Drelang on 2019/06/28 16:32
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class SeckillController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.queryAll();
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable("productId") long productId) {
        return productService.queryById(productId);
    }

    @PostMapping("/{productId}/exposer")
    public Exposer exposer(@PathVariable("productId") long productId) {
        return productService.exportUrl(productId);
    }

    @PostMapping("/{productId}/execution")
    public ExecutionSeckill execute(@PathVariable("productId") long productId,
                                    @RequestBody ExecutionSeckillParam param) {
        return productService.executeKill(productId, param.getBcrypt(), param.getMobile());

    }
}


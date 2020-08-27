package com.itjiguang.yanxuan.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.goods.api.IGoodsSpecService;
import com.itjiguang.yanxuan.model.GoodsSpec;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/spec")
public class GoodsSpecController {

    @Reference
    private IGoodsSpecService goodsSpecService;

    @GetMapping("/{id}")
    public ResponseEntity<Specification> queryById(@PathVariable("id") Long id){
        // 根据主键查询
        Specification specification = goodsSpecService.queryById(id);

        return new ResponseEntity<>(specification, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResult<GoodsSpec>> query(Integer currentPage, Integer pageSize, GoodsSpec goodsSpec){
        // 分页参数的判断处理
        if(currentPage==null || pageSize == null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 查询
        PageResult<GoodsSpec> pageResult = goodsSpecService.pageQuery(currentPage, pageSize, goodsSpec);

        return new ResponseEntity<>(pageResult , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Specification specification){
        int result = goodsSpecService.save(specification);
        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        int result = goodsSpecService.deleteById(id);
        if(result>0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Specification specification){
        int result = goodsSpecService.update(specification);
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

package com.itjiguang.yanxuan.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.goods.api.IGoodsCategoryService;
import com.itjiguang.yanxuan.model.GoodsCategory;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class GoodsCategoryController {

    @Reference
    private IGoodsCategoryService goodsCategoryService;

    @GetMapping
    public ResponseEntity<PageResult<GoodsCategory>> query(Integer currentPage, Integer pageSize, GoodsCategory goodsCategory){
        // 处理分页参数
        if(currentPage ==null || pageSize == null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程服务完成分页查询
        PageResult<GoodsCategory> pageResult = goodsCategoryService.pageQuery(currentPage, pageSize, goodsCategory);

        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> queryById(@PathVariable("id") Long id){
        // 根据主键进行查询
        Category category = goodsCategoryService.queryById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Category category){
        int result = goodsCategoryService.save(category);

        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Category category){
        int result = goodsCategoryService.update(category);

        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

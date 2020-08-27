package com.itjiguang.yanxuan.seller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.detail.api.IGoodsDetailService;
import com.itjiguang.yanxuan.goods.api.IGoodsInfoService;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsInfoService goodsInfoService;
    /*@Reference
    private IGoodsDetailService goodsDetailService;*/

    @PostMapping
    public ResponseEntity save(@RequestBody GoodsInfo goodsInfo){
        // 获取当前登陆人的账户名称
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        goodsInfo.setCreatePerson(username);
        // 调用远程服务完成商品信息的保存
        int result = goodsInfoService.save(goodsInfo);

        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<PageResult<GoodsSpu>> query(Integer currentPage, Integer pageSize, GoodsSpu goodsSpu){
        // 先处理分页参数
        if(currentPage==null || pageSize ==null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }

        // 获取当前的登录人
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        goodsSpu.setCreatePerson(username);

        // 调用远程的服务进行查询
        PageResult<GoodsSpu> pageResult = goodsInfoService.pageQuery(currentPage, pageSize, goodsSpu);

        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsInfo> queryById(@PathVariable("id") Long id){
        // 调用远程服务根据主键ID查询
        GoodsInfo goodsInfo = goodsInfoService.queryById(id);

       /* try {
            // 生成html
            goodsDetailService.createHtml(id);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new ResponseEntity<>(goodsInfo, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody GoodsInfo goodsInfo){
        // 当前登录人用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        goodsInfo.setUpdatePerson(username);
        // 调用远程服务进行修改操作
        int result = goodsInfoService.update(goodsInfo);
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

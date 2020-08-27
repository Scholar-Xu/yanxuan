package com.itjiguang.yanxuan.ad.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.ad.api.IAdTypeService;
import com.itjiguang.yanxuan.model.AdType;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adType")
public class AdTypeController {

    @Reference
    private IAdTypeService adTypeService;

    @GetMapping
    public ResponseEntity<PageResult<AdType>> query(Integer currentPage, Integer pageSize, AdType adType){
        if(currentPage==null || pageSize ==null){
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程服务进行查询
        PageResult<AdType> pageResult = adTypeService.pageQuery(currentPage, pageSize, adType);

        return new ResponseEntity<>(pageResult, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity save(@RequestBody AdType adType){
        int result = adTypeService.save(adType);
        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AdType adType){
        int result = adTypeService.update(adType);
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        int result = adTypeService.deleteById(id);
        if(result>0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

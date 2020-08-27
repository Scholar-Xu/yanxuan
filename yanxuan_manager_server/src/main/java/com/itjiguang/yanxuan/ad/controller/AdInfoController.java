package com.itjiguang.yanxuan.ad.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.ad.api.IAdInfoService;
import com.itjiguang.yanxuan.model.AdInfo;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adInfo")
public class AdInfoController {

    @Reference
    private IAdInfoService adInfoService;

    @GetMapping
    public ResponseEntity<PageResult<AdInfo>> query(Integer currentPage, Integer pageSize, AdInfo adInfo){
        if(currentPage==null|| pageSize==null){
            currentPage=1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程服务进行查询
        PageResult pageResult = adInfoService.pageQuery(currentPage, pageSize, adInfo);
        // 响应请求
        return new ResponseEntity<>(pageResult, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity save(@RequestBody AdInfo adInfo){
        int result = adInfoService.save(adInfo);
        if(result>0){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AdInfo adInfo){
        int result = adInfoService.update(adInfo);
        if(result>0){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        int result = adInfoService.deleteById(id);
        if(result>0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdInfo> queryById(@PathVariable("id") Long id){
        AdInfo adInfo = adInfoService.queryById(id);

        return new ResponseEntity<>(adInfo, HttpStatus.OK);
    }
}

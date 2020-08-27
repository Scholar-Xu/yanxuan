package com.itjiguang.yanxuan.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itjiguang.yanxuan.goods.api.IGoodsBrandService;
import com.itjiguang.yanxuan.model.GoodsBrand;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/brand")
public class GoodsBrandController {

    @Reference
    private IGoodsBrandService goodsBrandService;

    /**
     * GET http://localhost:9901/brand/11
     * GET http://localhost:9901/brand?pageNum=1&pageSize=10&name=极光
     *
     * POST http://localhost:9901/brand
     * PUT http://localhost:9901/brand
     * PATCH http://localhost:9901/brand
     *
     * DELETE http://localhost:9901/brand/11
     * @return
     */
    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<GoodsBrand> queryById(@PathVariable("id") Long id){
        // 调用远程服务进行根据ID查询得到
        GoodsBrand goodsBrand = goodsBrandService.queryBydId(id);
        // 向客户端响应查询得到的数据和状态码
        return new ResponseEntity<>(goodsBrand, HttpStatus.OK);
    }

    /**
     * 根据条件进行分页查询，查询多个数据
     * @param currentPage
     * @param pageSize
     * @param goodsBrand
     * @return
     */
    @GetMapping
    public ResponseEntity<PageResult<GoodsBrand>> query(Integer currentPage, Integer pageSize, GoodsBrand goodsBrand){
        if(currentPage ==null || pageSize ==null){
            // 查询所有, 理解成查询第一页，记录数是非常大的一值
            // goodsBrandService.queryAll();
            currentPage = 1;
            pageSize = Integer.MAX_VALUE;
        }
        // 调用远程服务进行分页条件查询
        PageResult<GoodsBrand> pageResult = goodsBrandService.pageQuery(currentPage, pageSize, goodsBrand);
        return new ResponseEntity<PageResult<GoodsBrand>>(pageResult, HttpStatus.OK);
    }

    /**
     * 新增保存数据
     * @param goodsBrand
     */
    @PostMapping
    public ResponseEntity save(@RequestBody GoodsBrand goodsBrand){
        int result = goodsBrandService.save(goodsBrand);

        if(result>0){
            // 如果成功,返回Created
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            // 如果失败返回 500
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 修改数据
     * @param goodsBrand
     */
    @PutMapping
    public ResponseEntity update(@RequestBody GoodsBrand goodsBrand){
        int result = goodsBrandService.update(goodsBrand);

        if(result>0){
            // 如果成功,返回Created
            return new ResponseEntity(HttpStatus.OK);
        }else{
            // 如果失败返回 500
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除数据
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        int result = goodsBrandService.deleteById(id);

        if(result>0){
            // 如果成功,返回Created
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            // 如果失败返回 500
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    // http://localhost:9901/brand/queryAll
    @RequestMapping(value="/queryAll", method = RequestMethod.GET)
//    @ResponseBody
    public List<GoodsBrand> queryAll(){
        // 调用远程服务完成品牌信息的查询
        List<GoodsBrand> goodsBrandList = goodsBrandService.queryAll();

        return goodsBrandList;
    }

    @RequestMapping("/pageQuery")
    public PageResult<GoodsBrand> pageQuery(Integer currentPage, Integer pageSize, GoodsBrand goodsBrand){
        // 调用远程服务完成分页数据的查询
        PageResult<GoodsBrand> pageResult = goodsBrandService.pageQuery(currentPage, pageSize, goodsBrand);

        return pageResult;
    }

    @PostMapping("/save")
    public Result saveBrand(@RequestBody GoodsBrand goodsBrand){
        Result result = new Result();
        try{
            goodsBrandService.save(goodsBrand);
            // 返回成功
            result.setCode(true);
            result.setMessage("品牌信息保存成功");
        }catch (Exception e){
            // 返回失败
            result.setCode(false);
            result.setMessage("品牌信息保存失败");
        }
        return result;
    }

    @PostMapping("/update")
    public Result updateBrand(@RequestBody GoodsBrand goodsBrand){
        Result result = new Result();
        try{
            goodsBrandService.update(goodsBrand);
            // 返回成功
            result.setCode(true);
            result.setMessage("品牌信息修改成功");
        }catch (Exception e){
            // 返回失败
            result.setCode(false);
            result.setMessage("品牌信息修改失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result deleteById(Long id){
        Result result = new Result();
        try{
            goodsBrandService.deleteById(id);
            // 返回成功
            result.setCode(true);
            result.setMessage("品牌信息删除成功");
        }catch (Exception e){
            // 返回失败
            result.setCode(false);
            result.setMessage("品牌信息删除失败");
        }
        return result;
    }
}

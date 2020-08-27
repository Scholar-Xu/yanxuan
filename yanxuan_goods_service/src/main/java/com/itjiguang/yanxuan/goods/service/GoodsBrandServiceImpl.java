package com.itjiguang.yanxuan.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.goods.api.IGoodsBrandService;
import com.itjiguang.yanxuan.mapper.GoodsBrandMapper;
import com.itjiguang.yanxuan.model.GoodsBrand;
import com.itjiguang.yanxuan.model.GoodsBrandExample;
import com.itjiguang.yanxuan.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsBrandServiceImpl implements IGoodsBrandService {

    // 注入Dao
    @Autowired
    private GoodsBrandMapper goodsBrandMapper;

    @Override
    public List<GoodsBrand> queryAll() {
        return goodsBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult<GoodsBrand> pageQuery(Integer currentPage, Integer pageSize) {

        // 开启分页
        PageHelper.startPage(currentPage, pageSize);

        // 进行查询数据
        Page<GoodsBrand> pageData = (Page<GoodsBrand>) goodsBrandMapper.selectByExample(null);

        // 构建返回结果
        PageResult<GoodsBrand> pageResult = new PageResult<GoodsBrand>();
        pageResult.setTotal(pageData.getTotal());
        pageResult.setResult(pageData.getResult());

        return pageResult;
    }

    @Override
    public PageResult<GoodsBrand> pageQuery(Integer currentPage, Integer pageSize, GoodsBrand goodsBrand) {
        // 构造查询条件
        GoodsBrandExample goodsBrandExample = new GoodsBrandExample();
        // 根据中文名称进行模糊查询
        if(goodsBrand!=null && goodsBrand.getName()!=null && !"".equals(goodsBrand.getName())){
            goodsBrandExample.createCriteria().andNameLike("%"+goodsBrand.getName()+"%");
        }

        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 进行查询数据
        Page<GoodsBrand> pageData = (Page<GoodsBrand>) goodsBrandMapper.selectByExample(goodsBrandExample);

        // 构建返回结果
        PageResult<GoodsBrand> pageResult = new PageResult<GoodsBrand>();
        pageResult.setTotal(pageData.getTotal());
        pageResult.setResult(pageData.getResult());

        return pageResult;
    }

    @Override
    public int save(GoodsBrand goodsBrand) {
        // 设置状态为正常
        goodsBrand.setIsDelete("0");
        // 保存
        return goodsBrandMapper.insert(goodsBrand);
    }

    @Override
    public int update(GoodsBrand goodsBrand) {
        return goodsBrandMapper.updateByPrimaryKeySelective(goodsBrand);
    }

    @Override
    public int deleteById(Long id) {
        // 进行物理删除，会直接把记录删除掉
        // goodsBrandMapper.deleteByPrimaryKey(id);

        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setId(id);
        goodsBrand.setIsDelete("1");
        // 执行逻辑删除操作
        return goodsBrandMapper.updateByPrimaryKeySelective(goodsBrand);

    }

    @Override
    public GoodsBrand queryBydId(Long id) {
        GoodsBrand goodsBrand = goodsBrandMapper.selectByPrimaryKey(id);
        return goodsBrand;
    }

}

package com.itjiguang.yanxuan.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.goods.api.IGoodsSpecService;
import com.itjiguang.yanxuan.mapper.GoodsSpecMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpecOptionMapper;
import com.itjiguang.yanxuan.model.GoodsSpec;
import com.itjiguang.yanxuan.model.GoodsSpecExample;
import com.itjiguang.yanxuan.model.GoodsSpecOption;
import com.itjiguang.yanxuan.model.GoodsSpecOptionExample;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.Specification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsSpecServiceImpl implements IGoodsSpecService {

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;
    @Autowired
    private GoodsSpecOptionMapper goodsSpecOptionMapper;

    @Override
    public PageResult<GoodsSpec> pageQuery(Integer currentPage, Integer pageSize, GoodsSpec goodsSpec) {
        // 构建查询条件
        GoodsSpecExample goodsSpecExample = new GoodsSpecExample();
        if(goodsSpec!=null){
            if(goodsSpec.getName()!=null && !"".equals(goodsSpec.getName())){
                goodsSpecExample.createCriteria().andNameLike("%"+goodsSpec.getName()+"%");
            }
        }
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询
        Page<GoodsSpec> pageData = (Page<GoodsSpec>)goodsSpecMapper.selectByExample(goodsSpecExample);

        // 构建返回结果
        PageResult<GoodsSpec> pageResult = new PageResult<>();
        pageResult.setTotal(pageData.getTotal());
        pageResult.setResult(pageData.getResult());

        return pageResult;
    }

    @Override
    public int save(Specification specification) {
        // 设置状态为有效
        specification.setStatus("0");
        // 保存规格信息
        int insert = goodsSpecMapper.insert(specification);
        // 保存规格项信息
        for (GoodsSpecOption option:specification.getOptionList()) {
            option.setStatus("0");
            option.setSpecId(specification.getId());
            goodsSpecOptionMapper.insertSelective(option);
        }
        return insert;
    }

    @Override
    public int deleteById(Long id) {
        // 规格信息
        GoodsSpec goodsSpec = new GoodsSpec();
        goodsSpec.setId(id);
        goodsSpec.setStatus("1");
        // 更新规格信息
        int update = goodsSpecMapper.updateByPrimaryKeySelective(goodsSpec);

        // 规格项信息
        GoodsSpecOptionExample goodsSpecOptionExample = new GoodsSpecOptionExample();
        goodsSpecOptionExample.createCriteria().andSpecIdEqualTo(id);
        // 更新的内容
        GoodsSpecOption goodsSpecOption = new GoodsSpecOption();
        goodsSpecOption.setStatus("1");
        goodsSpecOptionMapper.updateByExampleSelective(goodsSpecOption, goodsSpecOptionExample);

        return update;
    }

    @Override
    public Specification queryById(Long id) {
        // 查询规格信息
        GoodsSpec goodsSpec = goodsSpecMapper.selectByPrimaryKey(id);
        // 查询规格项
        GoodsSpecOptionExample goodsSpecOptionExample = new GoodsSpecOptionExample();
        goodsSpecOptionExample.createCriteria().andSpecIdEqualTo(id);
        // 查询
        List<GoodsSpecOption> optionList = goodsSpecOptionMapper.selectByExample(goodsSpecOptionExample);

        // 构建返回结果
        Specification specification = new Specification();
        BeanUtils.copyProperties(goodsSpec, specification);
        specification.setOptionList(optionList);

        return specification;
    }

    @Override
    public int update(Specification specification) {
        specification.setStatus("0");
        // 更新规格信息
        int update = goodsSpecMapper.updateByPrimaryKeySelective(specification);
        // 更新规格项信息 先删除，然后再保存
        // 根据specId的值进行删除
        GoodsSpecOptionExample goodsSpecOptionExample = new GoodsSpecOptionExample();
        goodsSpecOptionExample.createCriteria().andSpecIdEqualTo(specification.getId());
        goodsSpecOptionMapper.deleteByExample(goodsSpecOptionExample);
        // 保存规格项信息
        for (GoodsSpecOption option:specification.getOptionList()) {
            option.setStatus("0");
            option.setSpecId(specification.getId());
            goodsSpecOptionMapper.insertSelective(option);
        }
        return update;
    }

}

package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.GoodsCategoryBrandSpec;
import com.itjiguang.yanxuan.model.GoodsCategoryBrandSpecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryBrandSpecMapper {
    long countByExample(GoodsCategoryBrandSpecExample example);

    int deleteByExample(GoodsCategoryBrandSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategoryBrandSpec record);

    int insertSelective(GoodsCategoryBrandSpec record);

    List<GoodsCategoryBrandSpec> selectByExample(GoodsCategoryBrandSpecExample example);

    GoodsCategoryBrandSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsCategoryBrandSpec record, @Param("example") GoodsCategoryBrandSpecExample example);

    int updateByExample(@Param("record") GoodsCategoryBrandSpec record, @Param("example") GoodsCategoryBrandSpecExample example);

    int updateByPrimaryKeySelective(GoodsCategoryBrandSpec record);

    int updateByPrimaryKey(GoodsCategoryBrandSpec record);
}
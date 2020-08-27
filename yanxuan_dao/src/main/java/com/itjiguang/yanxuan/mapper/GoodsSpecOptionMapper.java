package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.GoodsSpecOption;
import com.itjiguang.yanxuan.model.GoodsSpecOptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecOptionMapper {
    long countByExample(GoodsSpecOptionExample example);

    int deleteByExample(GoodsSpecOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpecOption record);

    int insertSelective(GoodsSpecOption record);

    List<GoodsSpecOption> selectByExample(GoodsSpecOptionExample example);

    GoodsSpecOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpecOption record, @Param("example") GoodsSpecOptionExample example);

    int updateByExample(@Param("record") GoodsSpecOption record, @Param("example") GoodsSpecOptionExample example);

    int updateByPrimaryKeySelective(GoodsSpecOption record);

    int updateByPrimaryKey(GoodsSpecOption record);
}
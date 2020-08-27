package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.model.GoodsSpuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpuMapper {
    long countByExample(GoodsSpuExample example);

    int deleteByExample(GoodsSpuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    List<GoodsSpu> selectByExample(GoodsSpuExample example);

    GoodsSpu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByExample(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);
}
package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.SecondKillGoods;
import com.itjiguang.yanxuan.model.SecondKillGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondKillGoodsMapper {
    long countByExample(SecondKillGoodsExample example);

    int deleteByExample(SecondKillGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SecondKillGoods record);

    int insertSelective(SecondKillGoods record);

    List<SecondKillGoods> selectByExample(SecondKillGoodsExample example);

    SecondKillGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecondKillGoods record, @Param("example") SecondKillGoodsExample example);

    int updateByExample(@Param("record") SecondKillGoods record, @Param("example") SecondKillGoodsExample example);

    int updateByPrimaryKeySelective(SecondKillGoods record);

    int updateByPrimaryKey(SecondKillGoods record);
}
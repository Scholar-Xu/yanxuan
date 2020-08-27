package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.SecondKillOrder;
import com.itjiguang.yanxuan.model.SecondKillOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondKillOrderMapper {
    long countByExample(SecondKillOrderExample example);

    int deleteByExample(SecondKillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SecondKillOrder record);

    int insertSelective(SecondKillOrder record);

    List<SecondKillOrder> selectByExample(SecondKillOrderExample example);

    SecondKillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecondKillOrder record, @Param("example") SecondKillOrderExample example);

    int updateByExample(@Param("record") SecondKillOrder record, @Param("example") SecondKillOrderExample example);

    int updateByPrimaryKeySelective(SecondKillOrder record);

    int updateByPrimaryKey(SecondKillOrder record);
}
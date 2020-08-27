package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.PayLog;
import com.itjiguang.yanxuan.model.PayLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayLogMapper {
    long countByExample(PayLogExample example);

    int deleteByExample(PayLogExample example);

    int deleteByPrimaryKey(String payOrderNo);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    List<PayLog> selectByExample(PayLogExample example);

    PayLog selectByPrimaryKey(String payOrderNo);

    int updateByExampleSelective(@Param("record") PayLog record, @Param("example") PayLogExample example);

    int updateByExample(@Param("record") PayLog record, @Param("example") PayLogExample example);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);
}
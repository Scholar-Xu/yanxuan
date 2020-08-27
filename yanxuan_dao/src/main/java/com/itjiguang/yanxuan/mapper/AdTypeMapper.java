package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.AdType;
import com.itjiguang.yanxuan.model.AdTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdTypeMapper {
    long countByExample(AdTypeExample example);

    int deleteByExample(AdTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdType record);

    int insertSelective(AdType record);

    List<AdType> selectByExample(AdTypeExample example);

    AdType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdType record, @Param("example") AdTypeExample example);

    int updateByExample(@Param("record") AdType record, @Param("example") AdTypeExample example);

    int updateByPrimaryKeySelective(AdType record);

    int updateByPrimaryKey(AdType record);
}
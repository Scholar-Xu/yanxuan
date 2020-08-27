package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.SysCity;
import com.itjiguang.yanxuan.model.SysCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCityMapper {
    long countByExample(SysCityExample example);

    int deleteByExample(SysCityExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysCity record);

    int insertSelective(SysCity record);

    List<SysCity> selectByExample(SysCityExample example);

    SysCity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysCity record, @Param("example") SysCityExample example);

    int updateByExample(@Param("record") SysCity record, @Param("example") SysCityExample example);

    int updateByPrimaryKeySelective(SysCity record);

    int updateByPrimaryKey(SysCity record);
}
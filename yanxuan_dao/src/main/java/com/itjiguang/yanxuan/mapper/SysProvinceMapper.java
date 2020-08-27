package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.SysProvince;
import com.itjiguang.yanxuan.model.SysProvinceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysProvinceMapper {
    long countByExample(SysProvinceExample example);

    int deleteByExample(SysProvinceExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysProvince record);

    int insertSelective(SysProvince record);

    List<SysProvince> selectByExample(SysProvinceExample example);

    SysProvince selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysProvince record, @Param("example") SysProvinceExample example);

    int updateByExample(@Param("record") SysProvince record, @Param("example") SysProvinceExample example);

    int updateByPrimaryKeySelective(SysProvince record);

    int updateByPrimaryKey(SysProvince record);
}
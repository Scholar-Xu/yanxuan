package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.AccountAddress;
import com.itjiguang.yanxuan.model.AccountAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountAddressMapper {
    long countByExample(AccountAddressExample example);

    int deleteByExample(AccountAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountAddress record);

    int insertSelective(AccountAddress record);

    List<AccountAddress> selectByExample(AccountAddressExample example);

    AccountAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountAddress record, @Param("example") AccountAddressExample example);

    int updateByExample(@Param("record") AccountAddress record, @Param("example") AccountAddressExample example);

    int updateByPrimaryKeySelective(AccountAddress record);

    int updateByPrimaryKey(AccountAddress record);
}
package com.itjiguang.yanxuan.mapper;

import com.itjiguang.yanxuan.model.SellerShop;
import com.itjiguang.yanxuan.model.SellerShopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerShopMapper {
    long countByExample(SellerShopExample example);

    int deleteByExample(SellerShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerShop record);

    int insertSelective(SellerShop record);

    List<SellerShop> selectByExample(SellerShopExample example);

    SellerShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerShop record, @Param("example") SellerShopExample example);

    int updateByExample(@Param("record") SellerShop record, @Param("example") SellerShopExample example);

    int updateByPrimaryKeySelective(SellerShop record);

    int updateByPrimaryKey(SellerShop record);
}
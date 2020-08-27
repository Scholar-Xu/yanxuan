package com.itjiguang.yanxuan.seller.api;

import com.itjiguang.yanxuan.model.SellerShop;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.SellerInfo;

public interface SellerInfoService {

    /**
     * 保存商家入驻的信息
     * @param sellerInfo
     * @return
     */
    public int save(SellerInfo sellerInfo);

    /**
     * 根据条件进行查询，分页
     * @param currentPage
     * @param pageNum
     * @param sellerShop
     * @return
     */
    PageResult<SellerShop> pageQuery(Integer currentPage, Integer pageNum, SellerShop sellerShop);

    /**
     * 根据主键进行更新
     * @param sellerShop
     * @return
     */
    int update(SellerShop sellerShop);
}

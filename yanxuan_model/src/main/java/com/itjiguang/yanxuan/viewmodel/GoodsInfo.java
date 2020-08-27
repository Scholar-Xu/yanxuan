package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.GoodsSpu;

import java.util.List;

/**
 * 用来封装商品的信息
 */
public class GoodsInfo extends GoodsSpu {

    private List<GoodsSku> skuList;

    public List<GoodsSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<GoodsSku> skuList) {
        this.skuList = skuList;
    }
}

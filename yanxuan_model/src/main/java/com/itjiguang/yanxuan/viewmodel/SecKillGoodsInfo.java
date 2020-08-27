package com.itjiguang.yanxuan.viewmodel;

import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.model.SecondKillGoods;

public class SecKillGoodsInfo extends SecondKillGoods {

    // 商品信息
    private GoodsSpu goodsSpu;
    // 规格信息
    private GoodsSku goodsSku;
    // 时间差值（当前时间与开始或者结束时间）
    private Long seconds;
    // 标识 开始之前-before；开始之后结束之前-middle；结束之后-after
    private String secondsFlag;

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public String getSecondsFlag() {
        return secondsFlag;
    }

    public void setSecondsFlag(String secondsFlag) {
        this.secondsFlag = secondsFlag;
    }

    public GoodsSpu getGoodsSpu() {
        return goodsSpu;
    }

    public void setGoodsSpu(GoodsSpu goodsSpu) {
        this.goodsSpu = goodsSpu;
    }

    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
    }
}

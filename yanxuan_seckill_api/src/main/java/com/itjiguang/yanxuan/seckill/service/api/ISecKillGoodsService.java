package com.itjiguang.yanxuan.seckill.service.api;

import com.itjiguang.yanxuan.viewmodel.SecKillGoodsInfo;

public interface ISecKillGoodsService {

    /**
     * 根据id查询秒杀商品的信息、关联信息
     * @param id
     * @return
     */
    SecKillGoodsInfo queryById(Long id);
}

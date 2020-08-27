package com.itjiguang.yanxuan.detail.api;

public interface IGoodsDetailService {

    /**
     * 根据商品的主键ID生成对应的html详情页面
     * @param goodsId
     */
    public void createHtml(Long goodsId) throws Exception;


    public boolean deleteHtml(Long goodsId);
}

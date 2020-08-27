package com.itjiguang.yanxuan.seckill.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.mapper.GoodsSkuMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpuMapper;
import com.itjiguang.yanxuan.mapper.SecondKillGoodsMapper;
import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.model.SecondKillGoods;
import com.itjiguang.yanxuan.seckill.service.api.ISecKillGoodsService;
import com.itjiguang.yanxuan.viewmodel.SecKillGoodsInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Service
public class SecKillGoodsServiceImpl implements ISecKillGoodsService {

    @Autowired
    private SecondKillGoodsMapper secondKillGoodsMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  先从缓存中进行读取，如果没有，则从数据库中进行查询，并且存入到缓存中；
     *  如果有，直接返回
     * @param id
     * @return
     */
    @Override
    public SecKillGoodsInfo queryById(Long id) {
        // 从缓存中读取指定id的商品信息
        SecKillGoodsInfo goodsInfo = (SecKillGoodsInfo)redisTemplate.boundHashOps("seckillGoods").get(id.toString());

        if(goodsInfo == null ){
            goodsInfo = new SecKillGoodsInfo();
            // 查询秒杀商品的主信息
            SecondKillGoods secondKillGoods = secondKillGoodsMapper.selectByPrimaryKey(id);
            // spu信息
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(secondKillGoods.getGoodsId());
            // SKU信息
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(secondKillGoods.getSkuId());

            // 设置对应的值
            BeanUtils.copyProperties(secondKillGoods, goodsInfo);
            goodsInfo.setGoodsSpu(goodsSpu);
            goodsInfo.setGoodsSku(goodsSku);

            // 把数据存入到缓存中
            redisTemplate.boundHashOps("seckillGoods").put(id.toString(), goodsInfo);
        }

        // 倒计时逻辑处理
        // 标识
        Date nowDate = new Date();
        String flag = nowDate.before(goodsInfo.getStartDate())?
                    "before":
                    (nowDate.before(goodsInfo.getEndDate())?
                            "middle":"after");
        goodsInfo.setSecondsFlag(flag);
        // 计算时间差值
        if("before".equals(flag)){
            // 计算开始时间和当前系统时间的差值
            goodsInfo.setSeconds(goodsInfo.getStartDate().getTime() - nowDate.getTime());
        }else{
            goodsInfo.setSeconds(goodsInfo.getEndDate().getTime() - nowDate.getTime());
        }

        return goodsInfo;
    }
}

package com.itjiguang.yanxuan.quartz.job;

import com.itjiguang.yanxuan.mapper.GoodsSkuMapper;
import com.itjiguang.yanxuan.mapper.GoodsSpuMapper;
import com.itjiguang.yanxuan.mapper.SecondKillGoodsMapper;
import com.itjiguang.yanxuan.model.GoodsSku;
import com.itjiguang.yanxuan.model.GoodsSpu;
import com.itjiguang.yanxuan.model.SecondKillGoods;
import com.itjiguang.yanxuan.model.SecondKillGoodsExample;
import com.itjiguang.yanxuan.viewmodel.SecKillGoodsInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.spi.CalendarNameProvider;

/**
 * 假设，一天会进行多个秒杀场次： 8、10、12、14、16、18、20、22、24， 但是一个时间点只展示当前场次和后续的4个场次
 *      如果现在时8点之前，展示的是8点、10点、12点、14点、16点
 *      当10点时，8点场就应该失效了，从redis中移除，需要加入的数据：18点场次的秒杀商品信息
 *
 *
 *      执行的频率：0点开始，到16点，每两个小时执行一次
 *      实际执行的时间为 23.59 更新8点数据
 *      1.59 更新10点数据
 */
public class SecKillGoodsRedisJob extends QuartzJobBean {

    @Autowired
    private SecondKillGoodsMapper secondKillGoodsMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 完成秒杀商品的数据从Mysql到Redis中
     * 1. 从Mysql中查询数据
     * 2. 向Redis中保存数据
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获取当前执行的时间
        Date currentDate = context.getFireTime();
        // 根据当前时间获取8小时之后的时间点，10小时之后的时间点
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 8);
        // 秒杀商品的开始时间
        Date startDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        calendar.add(Calendar.MINUTE, 1);
        // 秒杀商品结束的时间
        Date endDate = calendar.getTime(); // 9:59
        // 创建查询条件
        SecondKillGoodsExample secondKillGoodsExample = new SecondKillGoodsExample();
        secondKillGoodsExample.createCriteria().andStartDateGreaterThanOrEqualTo(startDate).andEndDateLessThanOrEqualTo(endDate);
        List<SecondKillGoods> goodsList = secondKillGoodsMapper.selectByExample(secondKillGoodsExample);

        // 创建存放秒杀详细信息的集合
        HashMap<String, SecKillGoodsInfo> goodsInfoMap = new HashMap<>();
        HashMap<String, Integer> stockMap = new HashMap<>();

        for (SecondKillGoods goods : goodsList) {
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goods.getGoodsId());
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(goods.getSkuId());

            SecKillGoodsInfo secKillGoodsInfo = new SecKillGoodsInfo();
            BeanUtils.copyProperties(goods, secKillGoodsInfo);
            secKillGoodsInfo.setGoodsSpu(goodsSpu);
            secKillGoodsInfo.setGoodsSku(goodsSku);

            goodsInfoMap.put(secKillGoodsInfo.getId().toString(), secKillGoodsInfo);
            stockMap.put(secKillGoodsInfo.getId().toString(), secKillGoodsInfo.getStockCount());
        }


        // 保存秒杀商品详情
        redisTemplate.boundHashOps("seckillGoods").putAll(goodsInfoMap);
        // 保存库存信息
        redisTemplate.boundHashOps("secKillGoods_stock").putAll(stockMap);

    }
}

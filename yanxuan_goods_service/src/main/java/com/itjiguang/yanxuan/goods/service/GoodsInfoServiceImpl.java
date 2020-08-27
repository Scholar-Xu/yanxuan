package com.itjiguang.yanxuan.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.goods.api.IGoodsInfoService;
import com.itjiguang.yanxuan.mapper.*;
import com.itjiguang.yanxuan.model.*;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.viewmodel.GoodsInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsInfoServiceImpl implements IGoodsInfoService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SellerShopMapper sellerShopMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsBrandMapper goodsBrandMapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public int save(GoodsInfo goodsInfo) {
        // 查询店铺信息
        SellerShop sellerShop = this.querySellerShop(goodsInfo.getCreatePerson());
        // 设置店铺信息
        goodsInfo.setSellerId(sellerShop.getId());
        goodsInfo.setSellerName(sellerShop.getName());
        goodsInfo.setStatus("0");
        // 查询品牌的名称
        GoodsBrand goodsBrand = goodsBrandMapper.selectByPrimaryKey(goodsInfo.getBrandId());
        goodsInfo.setBrandName(goodsBrand.getName());
        // 查询类目的信息
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goodsInfo.getCategory3Id());
        goodsInfo.setCategoryName(goodsCategory.getStructName()+">"+goodsCategory.getName());
        // [商品的品牌] 商品的名称 商品的标题
        String label = "["+goodsInfo.getBrandName()+"] "+ goodsInfo.getName()+" "+goodsInfo.getLabel();
        goodsInfo.setLabel(label);
        // 保存商品的信息
        int insert = goodsSpuMapper.insert(goodsInfo);

        // 保存SkuList
        this.saveSkuList(goodsInfo);

        return insert;
    }

    @Override
    public PageResult<GoodsSpu> pageQuery(Integer currentPage, Integer pageSize, GoodsSpu goodsSpu) {
        // 创建查询条件
        GoodsSpuExample goodsSpuExample = new GoodsSpuExample();
        GoodsSpuExample.Criteria criteria = goodsSpuExample.createCriteria();
        // 根据商品的名称进行模糊查询
        if(goodsSpu.getName()!=null && !"".equals(goodsSpu.getName())){
            criteria.andNameLike("%"+goodsSpu.getName()+"%");
        }
        // 根据商品的状态进行查询
        if(goodsSpu.getStatus()!=null && !"".equals(goodsSpu.getStatus())){
            criteria.andStatusEqualTo(goodsSpu.getStatus());
        }
        if(goodsSpu.getCreatePerson()!=null && !"".equals(goodsSpu.getCreatePerson())){
            // 获取当前店铺的信息， createPerson所关联的店铺的信息
            SellerShop sellerShop = this.querySellerShop(goodsSpu.getCreatePerson());
            // 根据店铺的id进行查询
            if(sellerShop!=null){
                criteria.andSellerIdEqualTo(sellerShop.getId());
            }
        }
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        Page<GoodsSpu> pageData = (Page<GoodsSpu>)goodsSpuMapper.selectByExample(goodsSpuExample);
        // 创建返回结果
        PageResult<GoodsSpu> pageResult = new PageResult<>();
        pageResult.setResult(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());
        return pageResult;
    }

    @Override
    public GoodsInfo queryById(Long id) {
        // 先查询SPU信息
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(id);
        // 查询SKU信息
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        goodsSkuExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsSku> skuList = goodsSkuMapper.selectByExample(goodsSkuExample);
        // 构建返回结果
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(goodsSpu, goodsInfo);
        goodsInfo.setSkuList(skuList);
        return goodsInfo;
    }

    /**
     * 原始数据中包含SPU、SKU信息
     * 1. 根据主键更新SPU的信息
     * 2. 删除所有的SKU的信息
     * 3. 保存SKU的信息
     * @param goodsInfo
     * @return
     */
    @Override
    public int update(GoodsInfo goodsInfo) {
        if(!goodsInfo.getLabel().contains("[")){
            // [商品的品牌] 商品的名称 商品的标题
            String label = "["+goodsInfo.getBrandName()+"] "+ goodsInfo.getName()+" "+goodsInfo.getLabel();
            goodsInfo.setLabel(label);
        }

        // 根据主键更新SPU的信息
        int update = goodsSpuMapper.updateByPrimaryKey(goodsInfo);
        // 删除当前商品关联的SKU信息
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        goodsSkuExample.createCriteria().andGoodsIdEqualTo(goodsInfo.getId());
        goodsSkuMapper.deleteByExample(goodsSkuExample);
        // 保存新的SKU的信息
        this.saveSkuList(goodsInfo);
        return update;
    }

    @Override
    public int audit(GoodsSpu goodsSpu) {
        // 设置SPU的信息
        int audit = goodsSpuMapper.updateByPrimaryKeySelective(goodsSpu);
        // 设置SKU
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        goodsSkuExample.createCriteria().andGoodsIdEqualTo(goodsSpu.getId());
        // 创建出更新的内容
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setStatus(goodsSpu.getStatus());
        goodsSkuMapper.updateByExampleSelective(goodsSku, goodsSkuExample);

        return audit;
    }


    /**
     * 根据登录人的用户查询得到关联的店铺的信息
     * @param username
     * @return
     */
    private SellerShop querySellerShop(String username){
        // 根据登录人的用户名获取得到店铺的信息
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(username);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        Account account = null;
        if(accountList!=null && accountList.size()>0){
            account = accountList.get(0);
        }
        // 查询店铺的信息
        SellerShopExample sellerShopExample = new SellerShopExample();
        sellerShopExample.createCriteria().andAccountIdEqualTo(account.getId());
        List<SellerShop> shopList = sellerShopMapper.selectByExample(sellerShopExample);
        SellerShop sellerShop = null;
        if(shopList!=null && shopList.size()>0){
            sellerShop = shopList.get(0);
        }

        return sellerShop;
    }

    /**
     * 保存SPU关联的SKU的信息
     * @param goodsInfo
     */
    private void saveSkuList(GoodsInfo goodsInfo){
        // 获取SKU的信息
        List<GoodsSku> skuList = goodsInfo.getSkuList();
        for (GoodsSku sku: skuList ) {
            // 设置goodsId
            sku.setGoodsId(goodsInfo.getId());
            // 设置标题
            sku.setLabel(goodsInfo.getLabel());
            // 设置品牌的名称
            sku.setBrandName(goodsInfo.getBrandName());
            // 设置类目的名称
            sku.setCategoryName(goodsInfo.getCategoryName());
            // 设置商铺的信息
            sku.setSellerId(goodsInfo.getSellerId());
            sku.setSellerName(goodsInfo.getSellerName());
            // 设置状态
            sku.setOnSale("0");
            sku.setStatus("0");
            goodsSkuMapper.insert(sku);
        }
    }
}

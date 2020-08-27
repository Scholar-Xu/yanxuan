package com.itjiguang.yanxuan.seller.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjiguang.yanxuan.mapper.AccountMapper;
import com.itjiguang.yanxuan.mapper.SellerShopMapper;
import com.itjiguang.yanxuan.model.Account;
import com.itjiguang.yanxuan.model.SellerShop;
import com.itjiguang.yanxuan.model.SellerShopExample;
import com.itjiguang.yanxuan.result.PageResult;
import com.itjiguang.yanxuan.seller.api.SellerInfoService;
import com.itjiguang.yanxuan.viewmodel.SellerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerShopMapper sellerShopMapper;
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 先保存account信息，然后保存sellershop的信息
     * @param sellerInfo
     * @return
     */
    @Override
    public int save(SellerInfo sellerInfo) {
        // 创建Account对象
        Account account = new Account();
        account.setLoginName(sellerInfo.getLoginName());
//        account.setPassword(sellerInfo.getPassword());  // 明文保存密码
        // 使用加密器保存密文密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bcPassword = passwordEncoder.encode(sellerInfo.getPassword());
        account.setPassword(bcPassword);
        account.setPhone(sellerInfo.getLinkmanPhone());
        account.setEmail(sellerInfo.getLinkmanEmail());
        account.setStatus("0");
        // 保存account
        accountMapper.insert(account);

        // 保存sellerShop信息
        sellerInfo.setAccountId(account.getId());
        // 设置成待审核
        sellerInfo.setStatus("0");
        int insert = sellerShopMapper.insert(sellerInfo);
        return insert;
    }

    @Override
    public PageResult<SellerShop> pageQuery(Integer currentPage, Integer pageNum, SellerShop sellerShop) {
        // 构建查询条件
        SellerShopExample sellerShopExample = new SellerShopExample();
        SellerShopExample.Criteria criteria = sellerShopExample.or();
        if(sellerShop != null){
            if(sellerShop.getName()!=null && !"".equals(sellerShop.getName())){
                criteria.andNameLike("%"+sellerShop.getName()+"%");
            }
            if (sellerShop.getStatus()!=null && !"".equals(sellerShop.getStatus())){
                criteria.andStatusEqualTo(sellerShop.getStatus());
            }
        }
        // 开启分页查询
        PageHelper.startPage(currentPage, pageNum);
        Page<SellerShop> pageData = (Page)sellerShopMapper.selectByExample(sellerShopExample);

        // 构建返回结构
        PageResult<SellerShop> pageResult = new PageResult<>();
        pageResult.setResult(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());
        return pageResult;
    }

    @Override
    public int update(SellerShop sellerShop) {
        int update = sellerShopMapper.updateByPrimaryKeySelective(sellerShop);
        return update;
    }
}

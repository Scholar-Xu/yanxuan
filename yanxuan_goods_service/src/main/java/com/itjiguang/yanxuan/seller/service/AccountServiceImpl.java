package com.itjiguang.yanxuan.seller.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.mapper.AccountMapper;
import com.itjiguang.yanxuan.mapper.SellerShopMapper;
import com.itjiguang.yanxuan.model.Account;
import com.itjiguang.yanxuan.model.AccountExample;
import com.itjiguang.yanxuan.model.SellerShop;
import com.itjiguang.yanxuan.model.SellerShopExample;
import com.itjiguang.yanxuan.seller.api.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SellerShopMapper sellerShopMapper;
    @Override
    public Account queryByUserName(String username) {
        // 创建查询条件
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(username);
        // 查询Account
        List<Account> accountList = accountMapper.selectByExample(accountExample);

        for (Account account : accountList) {
            // 根据account的id查询sellerShop的信息
            SellerShopExample sellerShopExample = new SellerShopExample();
            sellerShopExample.createCriteria().andAccountIdEqualTo(account.getId());
            List<SellerShop> sellerShops = sellerShopMapper.selectByExample(sellerShopExample);
            if(sellerShops.size()>0){
                if (sellerShops.get(0).getStatus().equals("1")){
                    return account;
                }
            }
        }
        return null;
    }
}

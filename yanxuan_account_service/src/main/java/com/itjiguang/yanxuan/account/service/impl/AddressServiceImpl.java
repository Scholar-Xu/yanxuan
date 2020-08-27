package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.IAddressService;
import com.itjiguang.yanxuan.mapper.AccountAddressMapper;
import com.itjiguang.yanxuan.mapper.AccountMapper;
import com.itjiguang.yanxuan.model.Account;
import com.itjiguang.yanxuan.model.AccountAddress;
import com.itjiguang.yanxuan.model.AccountAddressExample;
import com.itjiguang.yanxuan.model.AccountExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountAddressMapper addressMapper;
    @Override
    public int save(String loginName, AccountAddress accountAddress) {
        // 查询账户信息
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList.size()<=0){
            throw new RuntimeException("账户信息不存在，请检查");
        }
        Account account = accountList.get(0);
        accountAddress.setAccountId(account.getId());

        // 检查当前的地址是否是默认地址，如果是就需要把之前的默认地址设置为非默认地址
        if("1".equals(accountAddress.getIsDefault())){
            // 创建条件
            AccountAddressExample accountAddressExample = new AccountAddressExample();
            accountAddressExample.createCriteria().andAccountIdEqualTo(account.getId()).andIsDefaultEqualTo("1");
            AccountAddress accountAddress1 = new AccountAddress();
            accountAddress1.setIsDefault("0");
            addressMapper.updateByExampleSelective(accountAddress1, accountAddressExample);
        }

        int insert = addressMapper.insert(accountAddress);
        return insert;
    }

    @Override
    public List<AccountAddress> get(String loginName) {
        Account account = this.getAccount(loginName);
        // 根据Account的id查询收货地址信息
        // 创建条件
        AccountAddressExample accountAddressExample = new AccountAddressExample();
        accountAddressExample.createCriteria().andAccountIdEqualTo(account.getId());
        //
        List<AccountAddress> addressList = addressMapper.selectByExample(accountAddressExample);
        return addressList;
    }

    private Account getAccount(String loginName){
        // 查询账户信息
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList.size()<=0){
            throw new RuntimeException("账户信息不存在，请检查");
        }
        Account account = accountList.get(0);

        return account;
    }
}

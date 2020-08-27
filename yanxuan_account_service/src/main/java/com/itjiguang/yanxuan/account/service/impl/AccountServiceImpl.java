package com.itjiguang.yanxuan.account.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itjiguang.yanxuan.account.api.IAccountService;
import com.itjiguang.yanxuan.mapper.AccountMapper;
import com.itjiguang.yanxuan.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public String registAccount(Account account) {

        // 检查验证码是否一致，如果一致才进行保存
        // 获取redis中的验证码
        String redisSmsCode = (String)redisTemplate.boundHashOps("smsCode").get(account.getPhone());
        // 请求中的验证码
        String checkCode = account.getCheckCode();
        if(checkCode.equals(redisSmsCode)){
            // 对明文密码进行设置
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String bcryptPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(bcryptPassword);
            // 设置其他属性
            account.setCreateDate(new Date());
            account.setIsPhoneCheck("1");

            // 进行保存
            accountMapper.insert(account);
            return "OK";
        }else{
            return "验证码错误";
        }
    }
}

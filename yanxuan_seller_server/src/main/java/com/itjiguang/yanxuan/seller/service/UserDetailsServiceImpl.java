package com.itjiguang.yanxuan.seller.service;

import com.itjiguang.yanxuan.model.Account;
import com.itjiguang.yanxuan.seller.api.IAccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    // 远程的服务
    private IAccountService accountService;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据登录的名称加载User
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据登录的名称查询Account信息
        Account account = accountService.queryByUserName(username);

        if(account== null){
            return null;
        }

        // 创建权限
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(account.getLoginName(), account.getPassword(),list);
    }
}

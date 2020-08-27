package com.itjiguang.yanxuan.cart.cas;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 初始化权限列表
        ArrayList<GrantedAuthority> authorityArrayList = new ArrayList<>();
        // 添加ROLE_USER角色到列表中
        authorityArrayList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, "", authorityArrayList);
    }
}

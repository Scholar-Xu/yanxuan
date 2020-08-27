package com.itjiguang.yanxuan.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itjiguang.yanxuan.cart.api.ICartService;
import com.itjiguang.yanxuan.cart.util.CookieUtils;
import com.itjiguang.yanxuan.model.OrderGoods;
import com.itjiguang.yanxuan.viewmodel.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Reference
    private ICartService cartService;

    @PostMapping
    public ResponseEntity addCart(@RequestBody OrderGoods orderGoods){
        // 获取当前登录的用户
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();

        // 购物车信息的初始化
        List<CartInfo> cartInfoList = new ArrayList<>();

        // Cookie中读取购物车的信息
        String cartListCookie = CookieUtils.getCookieValue(request, "cartList", "UTF-8");
        if(cartListCookie!=null && !"".equals(cartListCookie)){
            cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
        }
        if("anonymousUser".equals(loginName)){
            // 调用远程服务完成新商品添加到购物车，orderGoods 添加到 cartInfoList
            cartInfoList = cartService.addCart(cartInfoList, orderGoods);
            // 写回到Cookie中
            cartListCookie = JSON.toJSONString(cartInfoList);
            CookieUtils.setCookie(request, response, "cartList", cartListCookie, true);
        }else{
            // 从Redis中读取已存在的购物车信息
            List<CartInfo> cartListRedis = cartService.getFromRedis(loginName);
            if(cartListRedis==null){
                cartListRedis = new ArrayList<CartInfo>();
            }
            // 合并购物车
            if(cartInfoList.size()>0){
                cartListRedis = cartService.mergeCartList(cartInfoList, cartListRedis);
                // 清空Cookie中的数据
                CookieUtils.deleteCookie(request, response, "cartList");
            }
            // 添加购物车的操作
            cartListRedis = cartService.addCart(cartListRedis, orderGoods);

            // 保存到Redis中
            cartService.saveToRedis(loginName, cartListRedis);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List> getCart(){
        // 获取当前登录的用户名
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();

        // 购物车信息的初始化
        List<CartInfo> cartInfoList = new ArrayList<>();

        // Cookie中读取购物车的信息
        String cartListCookie = CookieUtils.getCookieValue(request, "cartList", "UTF-8");
        if(cartListCookie!=null && !"".equals(cartListCookie)){
            cartInfoList = JSON.parseArray(cartListCookie, CartInfo.class);
        }

        if(!"anonymousUser".equals(loginName)){
            // 从Redis中读取数据
            List<CartInfo> cartListRedis = cartService.getFromRedis(loginName);
            if(cartListRedis == null){
                cartListRedis = new ArrayList<CartInfo>();
            }
            if(cartInfoList.size()>0){
                // 合并的操作
                cartInfoList = cartService.mergeCartList(cartInfoList, cartListRedis);
                // 清空cookie
                CookieUtils.deleteCookie(request, response, "cartList");
                // 保存Redis中的数据
                cartService.saveToRedis(loginName, cartInfoList);
            }else{
                cartInfoList = cartListRedis;
            }
        }

        return new ResponseEntity<>(cartInfoList, HttpStatus.OK);
    }
}

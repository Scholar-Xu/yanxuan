package com.itjiguang.yanxuan.order;

import com.itjiguang.yanxuan.viewmodel.OrderInfoViewModel;

public interface IOrderInfoService {

    /**
     * 保存指定用户的订单信息
     * @param loginName
     * @param orderInfoViewModel
     * @return
     */
    String saveOrderInfo(String loginName, OrderInfoViewModel orderInfoViewModel);
}

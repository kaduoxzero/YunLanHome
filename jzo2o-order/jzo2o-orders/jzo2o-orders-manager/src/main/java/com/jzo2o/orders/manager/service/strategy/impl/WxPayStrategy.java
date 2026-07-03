package com.jzo2o.orders.manager.service.strategy.impl;

import com.jzo2o.orders.manager.service.strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * 微信支付策略实现类
 */
@Component("wxPay")
public class WxPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("使用微信进行支付");
    }
}
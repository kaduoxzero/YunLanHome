package com.jzo2o.orders.manager.service.strategy.impl;

import com.jzo2o.orders.manager.service.strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * 阿里支付策略实现类
 */
@Component("aliPay")
public class AliPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("使用阿里进行支付");
    }
}
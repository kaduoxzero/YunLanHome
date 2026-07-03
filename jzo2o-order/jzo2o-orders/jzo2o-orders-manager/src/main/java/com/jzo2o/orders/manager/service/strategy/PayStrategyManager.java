package com.jzo2o.orders.manager.service.strategy;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 上下文环境类(策略管理类)
 */
@Component
public class PayStrategyManager {
    Map<String, PayStrategy> payStrategyMap;

    //维护所有策略对象
    @PostConstruct //此注解标注的方法会在当前对象PayStrategyManager创建之后自动执行
    public void init() {
        //key: 当前对象在容器中的id
        //value: 当前对象
        payStrategyMap = SpringUtil.getBeansOfType(PayStrategy.class);
    }

    //根据用户需求, 执行指定的策略对象
    public void pay(String key) {
        PayStrategy payStrategy = payStrategyMap.get(key);
        if (payStrategy == null) {
            throw new RuntimeException("暂不支持当前支付方式");
        }
        payStrategy.pay();
    }
}
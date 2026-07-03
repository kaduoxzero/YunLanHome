package com.jzo2o.orders.manager.strategy.impl;

import com.jzo2o.common.expcetions.ForbiddenOperationException;
import com.jzo2o.orders.base.enums.OrderStatusEnum;
import com.jzo2o.orders.base.mapper.OrdersCanceledMapper;
import com.jzo2o.orders.base.model.domain.OrdersCanceled;
import com.jzo2o.orders.base.model.dto.OrderUpdateStatusDTO;
import com.jzo2o.orders.base.service.IOrdersCommonService;
import com.jzo2o.orders.manager.model.dto.OrderCancelDTO;
import com.jzo2o.orders.manager.strategy.OrderCancelStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//普通用户取消未支付订单
@Component("1:NO_PAY")//用户类型:订单状态
public class CommonUserNoPayOrderCancelStrategy implements OrderCancelStrategy{

    @Autowired
    private OrdersCanceledMapper ordersCanceledMapper;

    @Autowired
    private IOrdersCommonService ordersCommonService;

    @Override
    public void cancel(OrderCancelDTO orderCancelDTO) {
        // 1) 更新订单状态
        //update orders set orders_status = 600 where id = 1 and orders_status = 0
        OrderUpdateStatusDTO orderUpdateStatusDTO = OrderUpdateStatusDTO.builder()
                .id(orderCancelDTO.getId())
                .originStatus(OrderStatusEnum.NO_PAY.getStatus())
                .targetStatus(OrderStatusEnum.CANCELED.getStatus())
                .build();
        Integer i = ordersCommonService.updateStatus(orderUpdateStatusDTO);
        if (i <= 0){
            throw new ForbiddenOperationException("订单取消失败");
        }

        // 2) 保存取消订单记录
        OrdersCanceled ordersCanceled = new OrdersCanceled();
        ordersCanceled.setId(orderCancelDTO.getId());//订单id
        ordersCanceled.setCancellerId(orderCancelDTO.getCurrentUserId());//当前用户id
        ordersCanceled.setCancelerName(orderCancelDTO.getCurrentUserName());//当前用户名称
        ordersCanceled.setCancellerType(orderCancelDTO.getCurrentUserType());//当前用户类型
        ordersCanceled.setCancelReason(orderCancelDTO.getCancelReason());//取消原因
        ordersCanceled.setCancelTime(LocalDateTime.now());//取消时间
        ordersCanceledMapper.insert(ordersCanceled);
    }
}
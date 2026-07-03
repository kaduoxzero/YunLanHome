package com.jzo2o.orders.manager.strategy.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jzo2o.common.expcetions.ForbiddenOperationException;
import com.jzo2o.orders.base.enums.OrderRefundStatusEnum;
import com.jzo2o.orders.base.enums.OrderStatusEnum;
import com.jzo2o.orders.base.mapper.OrdersCanceledMapper;
import com.jzo2o.orders.base.mapper.OrdersRefundMapper;
import com.jzo2o.orders.base.model.domain.OrdersCanceled;
import com.jzo2o.orders.base.model.domain.OrdersRefund;
import com.jzo2o.orders.base.model.dto.OrderUpdateStatusDTO;
import com.jzo2o.orders.base.service.IOrdersCommonService;
import com.jzo2o.orders.manager.model.dto.OrderCancelDTO;
import com.jzo2o.orders.manager.strategy.OrderCancelStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//普通用户派单状态取消订单
@Component("1:DISPATCHING")
public class CommonUserDispatchingOrderCancelStrategy implements OrderCancelStrategy {
    @Autowired
    private OrdersCanceledMapper ordersCanceledMapper;
    @Autowired
    private OrdersRefundMapper ordersRefundMapper;
    @Autowired
    private IOrdersCommonService ordersCommonService;

    //取消派单中订单
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(OrderCancelDTO orderCancelDTO) {
        // 1) 更新订单状态
        //update orders set orders_status = 700 ,  refund_status =1 where id = 1 and orders_status = 100
        OrderUpdateStatusDTO orderUpdateStatusDTO = OrderUpdateStatusDTO.builder()
                .id(orderCancelDTO.getId())
                .originStatus(OrderStatusEnum.DISPATCHING.getStatus())
                .targetStatus(OrderStatusEnum.CLOSED.getStatus())
                .refundStatus(OrderRefundStatusEnum.REFUNDING.getStatus())
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

        // 3) 保存待退款记录
        OrdersRefund ordersRefund = BeanUtil.copyProperties(orderCancelDTO, OrdersRefund.class);
        ordersRefundMapper.insert(ordersRefund);
    }
}
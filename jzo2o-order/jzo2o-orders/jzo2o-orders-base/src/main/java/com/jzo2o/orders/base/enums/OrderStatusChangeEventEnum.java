package com.jzo2o.orders.base.enums;

import com.jzo2o.statemachine.core.StatusChangeEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusChangeEventEnum implements StatusChangeEvent {
    PAYED(OrderStatusEnum.NO_PAY, OrderStatusEnum.DISPATCHING, "支付成功", "payed"),
    DISPATCH(OrderStatusEnum.DISPATCHING, OrderStatusEnum.NO_SERVE, "接单/抢单成功", "dispatch"),
    START_SERVE(OrderStatusEnum.NO_SERVE, OrderStatusEnum.SERVING, "开始服务", "start_serve"),
    COMPLETE_SERVE(OrderStatusEnum.SERVING, OrderStatusEnum.FINISHED, "完成服务", "complete_serve"),
    EVALUATE(OrderStatusEnum.FINISHED, OrderStatusEnum.FINISHED, "评价完成", "evaluate"),
    CANCEL(OrderStatusEnum.NO_PAY, OrderStatusEnum.CANCELED, "取消订单", "cancel"),
    SERVE_PROVIDER_CANCEL(OrderStatusEnum.NO_SERVE, OrderStatusEnum.DISPATCHING, "服务人员/机构取消订单", "serve_provider_cancel"),
    CLOSE_DISPATCHING_ORDER(OrderStatusEnum.DISPATCHING, OrderStatusEnum.CLOSED, "派单中订单关闭", "close_dispatching_order"),
    CLOSE_NO_SERVE_ORDER(OrderStatusEnum.NO_SERVE, OrderStatusEnum.CLOSED, "待服务订单关闭", "close_no_serve_order"),
    CLOSE_SERVING_ORDER(OrderStatusEnum.SERVING, OrderStatusEnum.CLOSED, "服务中订单关闭", "close_serving_order"),
    CLOSE_FINISHED_ORDER(OrderStatusEnum.FINISHED, OrderStatusEnum.CLOSED, "已完成订单关闭", "close_finished_order");

    private final OrderStatusEnum sourceStatus;

    private final OrderStatusEnum targetStatus;

    private final String desc;

    private final String code;

    @Override
    public com.jzo2o.statemachine.core.StatusDefine getSourceStatus() {
        return this.sourceStatus;
    }

    @Override
    public com.jzo2o.statemachine.core.StatusDefine getTargetStatus() {
        return this.targetStatus;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}

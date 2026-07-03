package com.jzo2o.orders.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.api.orders.dto.response.OrderResDTO;
import com.jzo2o.api.orders.dto.response.OrderSimpleResDTO;
import com.jzo2o.orders.base.model.domain.Orders;
import com.jzo2o.orders.manager.model.dto.OrderCancelDTO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-07-10
 */
public interface IOrdersManagerService extends IService<Orders> {

    /**
     * @param ids
     * @return
     */
    List<Orders> batchQuery(List<Long> ids);

    Orders queryById(Long id);

    /**
     * 滚动分页查询
     *
     * @param currentUserId 当前用户id
     * @param ordersStatus  订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     * @param sortBy        排序字段
     * @return 订单列表
     */
    List<OrderSimpleResDTO> consumerQueryList(Long currentUserId, Integer ordersStatus, Long sortBy);


    /**
     * 根据订单id查询
     *
     * @param id 订单id
     * @return 订单详情
     */
    OrderResDTO getDetail(Long id);
    /**
     * 订单评价
     *
     * @param ordersId 订单id
     */
    void evaluationOrder(Long ordersId);
    /**
     * 取消订单
     *
     * @param orderCancelDTO 取消订单参数
     */
    void cancel(OrderCancelDTO orderCancelDTO);

    /**
     * 取消待支付订单
     *
     * @param orderCancelDTO 取消订单对象
     */
    void cancelByNoPay(OrderCancelDTO orderCancelDTO);

    /**
     * 取消派单中订单
     *
     * @param orderCancelDTO 取消订单对象
     */
    void cancelByDispatching(OrderCancelDTO orderCancelDTO);
}

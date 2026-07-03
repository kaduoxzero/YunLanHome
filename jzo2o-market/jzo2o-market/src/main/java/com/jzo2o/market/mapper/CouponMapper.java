package com.jzo2o.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.market.model.domain.Coupon;
import com.jzo2o.market.model.dto.response.CountResDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 * @since 2023-09-16
 */
public interface CouponMapper extends BaseMapper<Coupon> {
    /**
     * 根据优惠券活动id集合查询优惠券领取数量集合
     *
     * @param activityIdList 优惠券活动id集合
     * @return 优惠券领取数量集合
     */
    List<CountResDTO> countByActivityIdList(List<Long> activityIdList);
}

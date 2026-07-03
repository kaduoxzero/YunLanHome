package com.jzo2o.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.market.model.domain.Activity;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 * @since 2023-09-16
 */
public interface ActivityMapper extends BaseMapper<Activity> {
    /**
     * 扣减库存
     *
     * @param id 活动id
     */
    @Update("update activity set stock_num = stock_num - 1 where id = #{id} and stock_num > 0")
    int deductStock(long id);
}

package com.jzo2o.foundations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import com.jzo2o.foundations.model.domain.Serve;
import com.jzo2o.foundations.model.dto.response.ServeAggregationSimpleResDTO;
import com.jzo2o.foundations.model.dto.response.ServeAggregationTypeSimpleResDTO;
import com.jzo2o.foundations.model.dto.response.ServeCategoryResDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author itcast
 * @since 2023-07-03
 */
public interface ServeMapper extends BaseMapper<Serve> {
    /**
     * 根据区域查询服务列表
     * @param regionId 区域id
     * @return
     */
    List<ServeResDTO> queryServeListByRegionId(@io.lettuce.core.dynamic.annotation.Param("regionId") Long regionId);

    /**
     * 查询指定区域下上架的服务分类及项目信息
     *
     * @param regionId 区域id
     * @return 服务分类及项目信息
     */
    List<ServeCategoryResDTO> findListByRegionId(Long regionId);
    /**
     * 查询指定区域下上架且热门的服务项目信息
     *
     * @param regionId 区域id
     * @return 服务项目信息
     */
    List<ServeAggregationSimpleResDTO> findServeListByRegionId(Long regionId);
    /**
     * 查询当前区域下上架服务对应的分类
     *
     * @param regionId 区域id
     * @return 当前区域下上架服务对应的分类
     */
    List<ServeAggregationTypeSimpleResDTO> findServeTypeListByRegionId(Long regionId);
    /**
     * 根据ID查询服务详情
     *
     * @param id 服务id
     * @return 服务详情
     */
    ServeAggregationResDTO findServeDetailById(Long id);
}

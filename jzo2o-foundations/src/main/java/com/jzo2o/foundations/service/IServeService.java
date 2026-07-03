package com.jzo2o.foundations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.model.domain.Serve;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.jzo2o.foundations.model.dto.response.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务类
 * @author kaduox
 * @since 2026-04-24
 */
public interface IServeService extends IService<Serve> {
    /**
     * 分页查询服务列表
     * @param servePageQueryReqDTO 查询条件
     * @return 分页结果
     */
    PageResult<ServeResDTO> page(ServePageQueryReqDTO servePageQueryReqDTO);

    /**
     * 批量新增
     *
     * @param serveUpsertReqDTOList 批量新增数据
     */
    void batchAdd(List<ServeUpsertReqDTO> serveUpsertReqDTOList);
    /**
     * 服务价格修改
     *
     * @param id    服务id
     * @param price 价格
     * @return 服务
     */
    Serve update(Long id, BigDecimal price);
    /**
     * 上架
     *
     * @param id         服务id
     */
    Serve onSale(Long id);

    /**
     *
     * @param id
     */
    void delete(Long id);
    /**
     * 区域服务下架
     *
     * @param id 区域服务id
     */
    void offSale(Long id);
    /**
     * 查询指定区域下上架的服务分类及项目信息
     *
     * @param regionId 区域id
     * @return 服务分类及项目信息
     */
    List<ServeCategoryResDTO> firstPageServeList(Long regionId);
    /**
     * 查询指定区域下上架且热门的服务项目信息
     *
     * @param regionId 区域id
     * @return 服务项目信息
     */
    List<ServeAggregationSimpleResDTO> hotServeList(Long regionId);
    /**
     * 查询服务详情
     *
     * @param id 服务id
     * @return 服务详情信息
     */
    ServeAggregationSimpleResDTO findById(Long id);
    /**
     * 查询当前区域下上架服务对应的分类
     *
     * @param regionId 区域id
     * @return 当前区域下上架服务对应的分类
     */
    List<ServeAggregationTypeSimpleResDTO> serveTypeList(Long regionId);
    /**
     * 服务搜索
     *
     * @param cityCode    城市编码
     * @param keyword     关键词
     * @param serveTypeId 服务类型id
     * @return 服务项目信息
     */
    List<ServeSimpleResDTO> search(String cityCode, String keyword, Long serveTypeId);
    /**
     * 根据ID查询服务详情
     *
     * @param id 服务id
     * @return 服务详情
     */
    ServeAggregationResDTO findServeDetailById(Long id);
}

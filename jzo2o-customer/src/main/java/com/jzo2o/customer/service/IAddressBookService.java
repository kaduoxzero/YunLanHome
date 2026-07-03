package com.jzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.domain.AddressBook;
import com.jzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.jzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;

import java.util.List;

/**
 * <p>
 * 地址薄 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-07-06
 */
public interface IAddressBookService extends IService<AddressBook> {

    /**
     * 根据用户id和城市编码获取地址
     *
     * @param userId 用户id
     * @param cityCode 城市编码
     * @return 地址编码
     */
    List<AddressBookResDTO> getByUserIdAndCity(Long userId, String cityCode);
    /**
     * 查询用户默认地址值
     *
     * @return 用户默认地址
     */
    AddressBookResDTO findDefaultAddress();
    /**
     * 新增地址
     *
     * @param addressBookUpsertReqDTO 地址信息
     */
    void add(AddressBookUpsertReqDTO addressBookUpsertReqDTO);
    /**
     * 分页查询
     *
     * @param addressBookPageQueryReqDTO 查询参数
     * @return 分页列表
     */
    PageResult<AddressBookResDTO> page(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO);
    /**
     * 修改地址簿
     *
     * @param id 主键
     * @param addressBookUpsertReqDTO 修改内容
     */
    void updateAddressBook(Long id, AddressBookUpsertReqDTO addressBookUpsertReqDTO);
    /**
     * 地址薄设为默认/取消默认
     * @param id 主键
     * @param flag 默认/取消默认
     */
    void updateDefaultStatus(Long id, Integer flag);
}

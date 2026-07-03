package com.jzo2o.customer.controller.inner;

import cn.hutool.core.bean.BeanUtil;
import com.jzo2o.api.customer.AddressBookApi;
import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import com.jzo2o.customer.model.domain.AddressBook;
import com.jzo2o.customer.service.IAddressBookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inner/address-book")
@Api(tags = "内部接口 - 普通用户地址簿相关接口")
public class InnerAddressBookController implements AddressBookApi {

    @Autowired
    private IAddressBookService addressBookService;

    /**
     * 根据地址簿ID获取地址详情信息
     */
    @GetMapping("/{id}")
    @Override
    public AddressBookResDTO detail(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return BeanUtil.copyProperties(addressBook, AddressBookResDTO.class);
    }
}
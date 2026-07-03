package com.jzo2o.customer.controller.consumer;

import cn.hutool.core.bean.BeanUtil;
import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.domain.AddressBook;
import com.jzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.jzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;
import com.jzo2o.customer.service.IAddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("consumerAddressBookController")
@RequestMapping("/consumer/address-book")
@Api(tags = "用户端 - 用户地址相关接口")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping("/defaultAddress")
    @ApiOperation("查询用户默认地址值")
    public AddressBookResDTO findDefaultAddress(){
        return addressBookService.findDefaultAddress();
    }

    @ApiOperation("新增地址")
    @PostMapping
    public void add(@RequestBody AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        addressBookService.add(addressBookUpsertReqDTO);
    }

    @ApiOperation("地址薄分页查询")
    @GetMapping("/page")
    public PageResult<AddressBookResDTO> page(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO) {
        return addressBookService.page(addressBookPageQueryReqDTO);
    }

    @ApiOperation("地址薄详情")
    @GetMapping("/{id}")
    public AddressBookResDTO detail(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return BeanUtil.toBean(addressBook, AddressBookResDTO.class);
    }

    @ApiOperation("地址薄修改")
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        addressBookService.updateAddressBook(id, addressBookUpsertReqDTO);
    }

    @ApiOperation("地址薄批量删除")
    @DeleteMapping("/batch")
    public void logicallyDelete(@RequestBody List<Long> ids) {
        addressBookService.removeByIds(ids);
    }

    @ApiOperation("地址薄设为默认/取消默认")
    @PutMapping("/default")
    public void updateDefaultStatus(Long id, Integer flag) {
        addressBookService.updateDefaultStatus(id, flag);
    }
}

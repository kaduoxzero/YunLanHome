package com.jzo2o.api.customer;

import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping; // 必须导入
import org.springframework.web.bind.annotation.RequestParam; // 必须导入
import javax.validation.constraints.NotNull;

@FeignClient(contextId = "jzo2o-customer", value = "jzo2o-customer", path = "/customer/inner/address-book")
public interface AddressBookApi {

    /**
     * 根据id查询地址详情
     *
     * 1. 必须加上 @GetMapping (或其他请求方式注解)
     * 2. Feign调用中，基础类型的参数必须加上 @RequestParam 或 @PathVariable
     */
    @GetMapping("/detail")
    AddressBookResDTO detail(@RequestParam("addressBookId") @NotNull(message = "您还未选择服务地址") Long addressBookId);
}
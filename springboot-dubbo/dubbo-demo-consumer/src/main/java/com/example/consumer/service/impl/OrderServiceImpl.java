package com.example.consumer.service.impl;

import domain.UserAddress;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import service.OrderService;
import service.UserService;

import java.util.List;

/**
 * <pre>
 * 订单服务
 * </pre>
 *
 * @author nicky
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2020年01月05日  修改内容:
 * </pre>
 */
@Service
@Component
public class OrderServiceImpl implements OrderService {
    @Reference(loadbalance = "random", timeout = 1000) //dubbo直连
    UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        //1、查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        return addressList;
    }
}
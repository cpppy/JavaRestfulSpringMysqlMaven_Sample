package com.mindflow.restful.jersey.service;

import com.mindflow.restful.jersey.domain.Order;

import java.util.List;


public interface IOrderService {

    List<Order> getOrderList();

    Order findOrderById(Long id);

    long insert(Order order);

    int update(Order order);

    long delete();

    int deleteOrderById(Long id);
}

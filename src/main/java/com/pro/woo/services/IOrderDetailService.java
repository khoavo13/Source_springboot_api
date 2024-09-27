package com.pro.woo.services;

import com.pro.woo.dtos.OrderDetailDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;
    OrderDetail getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetail updateOrderDetail(Long id,OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    void deleteById(Long id) ;
    List<OrderDetail> findByOrderId(Long orderId);
}

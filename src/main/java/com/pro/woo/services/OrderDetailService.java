package com.pro.woo.services;

import com.pro.woo.dtos.OrderDetailDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.Order;
import com.pro.woo.models.OrderDetail;
import com.pro.woo.models.Product;
import com.pro.woo.repositories.OrderDetailRepository;
import com.pro.woo.repositories.OrderRepository;
import com.pro.woo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        Order order=orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Order not found"));
        Product product=productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Product not found"));;
    OrderDetail orderDetail=OrderDetail.builder()
            .order(order)
            .product(product)
            .numberOfProducts(orderDetailDTO.getNumberOfProduct())
            .price(orderDetailDTO.getPrice())
            .totalMoney(orderDetailDTO.getTotalMoney())
            .color(orderDetailDTO.getColor())
            .build();
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Order Not found"));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
       OrderDetail existingOrderDetail=orderDetailRepository.findById(id)
               .orElseThrow(()-> new DataNotFoundException("Order detail Not found"));
       Order existingOrder=orderRepository.findById(orderDetailDTO.getOrderId())
               .orElseThrow(()-> new DataNotFoundException("Order Not found"));
       Product existingProduct=productRepository.findById(orderDetailDTO.getProductId())
               .orElseThrow(()-> new DataNotFoundException("Product not found"));
       existingOrderDetail.setOrder(existingOrder);
       existingOrderDetail.setProduct(existingProduct);
       existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProduct());
       existingOrderDetail.setPrice(orderDetailDTO.getPrice());
       existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
       existingOrderDetail.setColor(orderDetailDTO.getColor());
       return  orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}

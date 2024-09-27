package com.pro.woo.services;

import com.pro.woo.dtos.OrderDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.Order;
import com.pro.woo.models.OrderStatus;
import com.pro.woo.models.User;
import com.pro.woo.repositories.OrderRepository;
import com.pro.woo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Override
    public Order createOrder(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
       modelMapper.typeMap(OrderDTO.class,Order.class)
               .addMappings(mapper->mapper.skip(Order::setId));
     Order order = new Order();
     modelMapper.map(orderDTO, order);
     order.setUser(user);
     order.setOrderDate(new Date());
     order.setStatus(OrderStatus.PENDING);
     LocalDate shippingDate=orderDTO.getShippingDate()
             ==null? LocalDate.now()
             : orderDTO.getShippingDate();
     if(shippingDate.isBefore(LocalDate.now())){
         throw new DataNotFoundException("Shipping Date is before current date");
     }
     order.setShippingDate(shippingDate);
     order.setActive(true);
     orderRepository.save(order);
     return order;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(()->new DataNotFoundException("Order not found"));
        User existingUser = userRepository.findById(orderDTO.getUserId()).orElseThrow(()->new DataNotFoundException("User not found"));
        modelMapper.typeMap(OrderDTO.class,Order.class)
                .addMappings(mapper->mapper.skip(Order::setId));
        modelMapper.map(orderDTO, order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

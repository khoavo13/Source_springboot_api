package com.pro.woo.controllers;

import com.pro.woo.dtos.OrderDTO;
import com.pro.woo.dtos.OrderDetailDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.OrderDetail;
import com.pro.woo.repositories.OrderDetailRepository;
import com.pro.woo.responses.OrderDetailResponse;
import com.pro.woo.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/orderDetail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id) throws DataNotFoundException {
        OrderDetail orderDetail = orderDetailService.getOrderDetail(id);
        return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(orderDetail));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId) throws DataNotFoundException {
        List<OrderDetail> orderDetails=orderDetailService.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses=orderDetails
                .stream()
                .map(OrderDetailResponse::fromOrderDetail)
                .toList();

        return ResponseEntity.ok().body(orderDetailResponses);
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errors);
            }

           OrderDetail orderDetail= orderDetailService.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok("insert successful"+ orderDetail);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                         @Valid @RequestBody OrderDetailDTO orderDetailDTO){
       try{
           OrderDetail orderDetail=orderDetailService.updateOrderDetail(id,orderDetailDTO);
           return ResponseEntity.ok().body(orderDetail);
       }
       catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        orderDetailService.deleteById(id);
        return ResponseEntity.ok("delete"+id);
    }
}

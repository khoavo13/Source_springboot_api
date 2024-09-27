package com.pro.woo.controllers;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.dtos.OrderDTO;
import com.pro.woo.models.Category;
import com.pro.woo.models.Order;
import com.pro.woo.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(
            @Valid @PathVariable("id") Long id){
        try{
            Order existingOrder=orderService.getOrder(id);
            return ResponseEntity.ok().body(existingOrder);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getAllOrders(
            @Valid @PathVariable("user_id") Long userId){
      try{
          List<Order> orders=orderService.findByUserId(userId);
          return ResponseEntity.ok().body(orders);
      }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody OrderDTO orderDTO, BindingResult result){
      try{
          if(result.hasErrors()){
              List<String> errors = result.getFieldErrors().stream()
                      .map(FieldError::getDefaultMessage).toList();
              return ResponseEntity.badRequest().body(errors);
          }
          Order order=orderService.createOrder(orderDTO);
          return ResponseEntity.ok("insert successfull");
      }catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                         @Valid @RequestBody OrderDTO orderDTO){

        try{
            Order order=orderService.updateOrder(id,orderDTO);
            return ResponseEntity.ok("update successfull "+order);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
      //change active
        orderService.deleteOrder(id);
        return ResponseEntity.ok("order delete"+id);
    }
}

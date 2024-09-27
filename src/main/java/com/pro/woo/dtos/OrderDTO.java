package com.pro.woo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value=1,message = "user' ID must be >0")
    private Long userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number must be at least 5 charaters")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("total_money")
    @Min(value=0,message = "Total money must be >0")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;
    @JsonProperty("payment_method")
    private String paymentMethod;


}
//{
//        "user_id":"1",
//        "fullname":"Lê Văn Nai",
//        "email":"naingongac@gmail.com",
//        "phone_number":"123456",
//        "address":"135 Hai Bà Trưng, HCM",
//        "note":"Hàng dễ vỡ",
//        "total_money":"150000",
//        "shipping_method":"PENDDING",
//        "payment_method":"cod"
//
//
//        }
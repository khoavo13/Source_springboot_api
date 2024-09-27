package com.pro.woo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {
    @JsonProperty("product_id")
    @Min(value=1,message="product' id must be >0")
    private Long productId;
    @Size(min=5,max=200,message = "image' name")
    @JsonProperty("image_url")
    private String imageUrl;

}

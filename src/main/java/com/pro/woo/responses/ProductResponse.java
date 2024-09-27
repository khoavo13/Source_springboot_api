package com.pro.woo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pro.woo.models.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse extends BaseResponse {
    private Long id;
    private String name;
    private Float price;
    private String thumbnai;
    private String description;

    @JsonProperty("category_id")
    private Long categoryId;
    public static ProductResponse fromProduct(Product product) {
        ProductResponse responseProduct = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .price(product.getPrice())
                .thumbnai(product.getThumbnail())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .build();
        responseProduct.setCreatedAt(product.getCreatedAt());
        responseProduct.setUpdatedAt(product.getUpdatedAt());
        return responseProduct;
    }
}

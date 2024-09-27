package com.pro.woo.services;

import com.pro.woo.dtos.ProductDTO;
import com.pro.woo.dtos.ProductImageDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.Product;
import com.pro.woo.models.ProductImage;
import com.pro.woo.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(Long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    Product updateProduct(Long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(Long id);
    boolean existsByName(String name);
    List<ProductImage> getAllProductsImage(Long id);
    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;
}

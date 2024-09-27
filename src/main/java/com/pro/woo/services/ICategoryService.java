package com.pro.woo.services;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.models.Category;
import com.pro.woo.responses.CategoryResponse;
import com.pro.woo.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(Long categoryId);
    Category updateCategory(Long categoryId, CategoryDTO category);

    Page<CategoryResponse> getAllCategories(PageRequest pageRequest);
    void deleteCategory(Long id);

}


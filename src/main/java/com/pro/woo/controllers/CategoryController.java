package com.pro.woo.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.woo.dtos.CategoryDTO;
import com.pro.woo.responses.CategoryListResponse;
import com.pro.woo.responses.CategoryResponse;
import com.pro.woo.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
//@Validated
public class CategoryController {
    private final CategoryService categoryService;
    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public ResponseEntity<CategoryListResponse> getAllCategories(@RequestParam("page") int page,
                                        @RequestParam("limit") int limit){
        PageRequest pageRequest=PageRequest.of(
                page,limit,
                Sort.by("createdAt").descending()
        );
        Page<CategoryResponse> categoryResponsePage=categoryService.getAllCategories(pageRequest);
        int totalPages=categoryResponsePage.getTotalPages();
        List<CategoryResponse> responseCategories=categoryResponsePage.getContent();
        return ResponseEntity.ok(CategoryListResponse.builder()
                .categories(responseCategories)
                .totalPages(totalPages)
                .build());
    }
    @PostMapping("")
    public ResponseEntity<?> insert(@Valid @RequestBody CategoryDTO categoryDTO,
                                    BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("insert"+categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok("edit"+id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete"+id);
    }

//    @DeleteMapping("/y")
//    public ResponseEntity<String> delete1(){
//        return ResponseEntity.ok("delete successfull");
//    }
}


// List<String> errors = new ArrayList<>();
// for (FieldError fieldError : result.getFieldErrors()) {
//     errors.add(fieldError.getDefaultMessage());
// }
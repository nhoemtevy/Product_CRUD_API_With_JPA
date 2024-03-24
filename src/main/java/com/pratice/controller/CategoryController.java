package com.pratice.controller;

import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryResponse;
import com.pratice.model.Category;
import com.pratice.model.Product;
import com.pratice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest request){
        categoryService.createNewCategory(request);
    }

    @PutMapping("{id}")
    CategoryResponse editCategoryById( @Valid @PathVariable Integer id, @RequestBody CategoryRequest request){
        return categoryService.editCategoryById(id, request);
    }

    @GetMapping
     List<CategoryResponse>findCategories() {
        return categoryService.findAllCategory();
    }
    @GetMapping("{id}")
    CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.findCategoryById(id);
    }

}

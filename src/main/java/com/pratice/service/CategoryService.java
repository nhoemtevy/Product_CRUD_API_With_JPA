package com.pratice.service;

import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void deleteCategoryById(Integer id);

    CategoryResponse editCategoryById(Integer id, CategoryRequest request);
    void createNewCategory(CategoryRequest request);
    CategoryResponse findCategoryByName(String name);
    CategoryResponse findCategoryById(Integer id);

    List<CategoryResponse> findAllCategory();
}

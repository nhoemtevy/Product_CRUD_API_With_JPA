package com.pratice.service.impl;

import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryRequest;
import com.pratice.dto.CategoryResponse;
import com.pratice.model.Category;
import com.pratice.repository.CategoryRepository;
import com.pratice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category has not been found!"
                ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
        return  this.findCategoryById(id);
    }

    @Override
    public void createNewCategory(CategoryRequest request) {

          if (categoryRepository.existsByName(request.name())) {
              throw new ResponseStatusException(
                      HttpStatus.CONFLICT,
                      "Category name already existed!"
              );
          }

         Category category = new Category();
         category.setName(request.name());
         category.setDescription(request.description());
         categoryRepository.save(category);
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category has not been found!"
                ));
        return new CategoryResponse(category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryResponse(category.getName(),
                category.getDescription())).toList();
    }
}

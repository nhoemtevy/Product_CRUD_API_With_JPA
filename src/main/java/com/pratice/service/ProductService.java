package com.pratice.service;

import com.pratice.dto.ProductCreateRequest;
import com.pratice.dto.ProductEditRequest;
import com.pratice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProduct();
    List<ProductResponse> findProducts(String name, Boolean status);
    ProductResponse findProductById(Integer id);
    ProductResponse findProductByUuid(String uuid);
      void createNewProduct(ProductCreateRequest request);
      ProductResponse editProductByUuid(String uuid, ProductEditRequest request);
      void deleteProductByUuid(String uuid);
}

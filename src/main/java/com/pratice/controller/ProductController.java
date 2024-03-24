package com.pratice.controller;

import com.pratice.dto.ProductCreateRequest;
import com.pratice.dto.ProductEditRequest;
import com.pratice.dto.ProductResponse;
import com.pratice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("{id}")
    ProductResponse findProductById (@PathVariable Integer id ){
        return  productService.findProductById(id);
    }
    @GetMapping()
    List<ProductResponse> findProduct() {
        return productService.findAllProduct();
    }
    @GetMapping("/uuid/{uuid}")
    ProductResponse findProductByUuid(@PathVariable String uuid) {
        return  productService.findProductByUuid(uuid);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct( @Valid @RequestBody ProductCreateRequest request){
        System.out.println(request);
        productService.createNewProduct(request);
    }
    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid,
                           @RequestBody ProductEditRequest request) {
        productService.editProductByUuid(uuid, request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }
}

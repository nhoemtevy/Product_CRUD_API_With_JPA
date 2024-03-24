package com.pratice.service.impl;

import com.pratice.dto.*;
import com.pratice.model.Product;
import com.pratice.repository.ProductRepository;
import com.pratice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(
                product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )
        ).toList();
    }
    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        List<Product> products = productRepository.findProductByNameAndStatus(name, status);
        System.out.println(products);
        return  products.stream()
                .map(
                        product ->  new ProductResponse(
                                product.getUuid(), product.getName(), product.getPrice(), product.getQty()

                        )).toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product has not been found!!")
        );
        return new ProductResponse(product.getUuid(), product.getName(), product.getPrice(),product.getQty());

    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        Product productFound = productRepository.findProductByUuid(uuid);
        if(productFound != null){
            return  new ProductResponse( productFound.getUuid(), productFound.getName(), productFound.getPrice(),productFound.getQty());
        }
        throw  new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not has been found!!"
        );
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        if(productRepository.existsByName(request.name())){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product already exited!!"
            );
        }
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setStatus(false);
        product.setImportedDate(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    public ProductResponse editProductByUuid(String uuid, ProductEditRequest request) {
        Product productFound = productRepository.findProductByUuid(uuid);
        if(productFound != null){
            productFound.setName(request.name());
            productFound.setPrice(request.price());
            productRepository.save(productFound);
            return this.findProductByUuid(uuid);
        }
        throw  new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not has been found!!"
        );
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        if (!productRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product productFound = productRepository.findProductByUuid(uuid);
        System.out.println(productFound.getId());
        productRepository.deleteById(productFound.getId());
    }
}

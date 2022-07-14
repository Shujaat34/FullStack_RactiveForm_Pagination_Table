package com.bukhari.pagination.service;

import com.bukhari.pagination.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Integer id,Product product);

    List<Product> findAllProducts();

    List<Product> findProductsWithSorting(String field);

    Page<Product> findProductsWithPagination(int offset, int pageSize);

    Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field);

    void deleteProduct(Integer id);
}

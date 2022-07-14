package com.bukhari.pagination.controller;

import com.bukhari.pagination.bean.APIResponse;
import com.bukhari.pagination.entity.Product;
import com.bukhari.pagination.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")

@Tag(name = "Product Services", description = "User can Test Services using Swagger")// Swagger 3.0
public class ProductController {

    //Vidoe Tutorial Link : https://www.youtube.com/watch?v=Wa0GQwWwzJE


    //Offset means Current Page

    @Autowired
    private ProductService service;

    @GetMapping
    private ResponseEntity<List<Product>> getProducts() {
        List<Product> allProducts = service.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{field}")
    private APIResponse<List<Product>> getProductsWithSort(@PathVariable String field) {
        List<Product> allProducts = service.findProductsWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }


    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = service.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = service.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateroduct(@PathVariable("id")Integer id,@RequestBody Product product) {
        return ResponseEntity.ok(service.updateProduct(id,product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateroduct(@PathVariable("id")Integer id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }



}

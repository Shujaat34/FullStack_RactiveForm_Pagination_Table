package com.bukhari.pagination.service;

import com.bukhari.pagination.entity.Product;
import com.bukhari.pagination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repository;

    String categ [] = {"Electronics","Fruit","Clothes"};

    String descrip [] = {"This is Good","This is Not Good","This is Perfect"};

    String durable [] = {"2 years", "4 years", "6 years"};
    //For First Time Execution to input the 200 records in db execute this first then comment it.
//    @PostConstruct
//    public void initDB() {
//        int price = new Random().nextInt(50000);
//        Long l_price = (long) price;
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Product("product" + i, categ[new Random().nextInt(3)],
//                        durable[new Random().nextInt(3)],
//                        descrip[new Random().nextInt(3)],
//                        l_price , "comment "+i, new Date()))
//                .collect(Collectors.toList());
//        repository.saveAll(products);
//    }


    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Integer id,Product product) {
        Product existingProd = repository.findById(id).get();
        existingProd.setName(product.getName());
        existingProd.setCategory(product.getCategory());
        existingProd.setDurability(product.getDurability());
        existingProd.setDescription(product.getDescription());
        existingProd.setPrice(product.getPrice());
        existingProd.setComment(product.getComment());
        existingProd.setDate(product.getDate());

        return repository.save(existingProd);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }


    public List<Product> findProductsWithSorting(String field){
        return  repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }

    @Override
    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}

package com.example.pagingandsorting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public void addProduct(Product product){
        this.productRepository.saveAndFlush(product);
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public Page<Product> pagination1(){

        Pageable secondPageWithThreeElements = PageRequest.of(2, 3);
        Page<Product> allProducts = productRepository.findAll(secondPageWithThreeElements);
        return allProducts;
    }

//    public List<Product> pagination2(){
//
//        Pageable page = PageRequest.of(0, 10);
//        List<Product> allTenDollarProducts = productRepository.findAllByPrice(10.0, (java.awt.print.Pageable) page).getContent();
//
//        return allTenDollarProducts;
//    }

    public List<Product> sort1(){

        List<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
        return allProductsSortedByName;
    }

    public Page<Product> pageAndSort1(){

        Pageable sortedByPriceDescNameAsc =
                PageRequest.of(1, 5, Sort.by("price").descending().and(Sort.by("name")));

        Page<Product> products = this.productRepository.findAll(sortedByPriceDescNameAsc);
        return products;
    }


    // https://www.youtube.com/watch?v=Wa0GQwWwzJE

    public List<Product> findProductsWithSorting(String field){
        return productRepository.findAll(Sort.by(field).descending());
    }

    public List<Product> findProductsWithPagination(int itermPerPage, int pageSize){

        Pageable pageable = PageRequest.of(itermPerPage, pageSize);
        return productRepository.findAll(pageable).getContent();

    }

    public List<Product> findProductsWithPaginationAndSorting(int itermPerPage, int pageSize, String field){

        Pageable pageable = PageRequest.of(itermPerPage, pageSize).withSort(Sort.by(field));
        return productRepository.findAll(pageable).getContent();
    }
}

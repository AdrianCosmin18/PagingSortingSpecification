package com.example.pagingandsorting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody Product product){
        this.productService.addProduct(product);
    }

    @GetMapping("/pagination1")
    public Page<Product> pagination1(){
        return this.productService.pagination1();
    }


//    @GetMapping("/pagination2")
//    public List<Product> pagination2(){
//        return this.productService.pagination2();
//    }

    @GetMapping("/sort1")
    public List<Product> sort1(){
        return this.productService.sort1();
    }

    @GetMapping("/ps1")
    public Page<Product> pageAndSort1(){
        return this.productService.pageAndSort1();
    }


    // https://www.youtube.com/watch?v=Wa0GQwWwzJE

    @GetMapping
    public APIResponse<List<Product>> getProducts(){
        List<Product> products = productService.getProducts();
        return new APIResponse<>(products.size(), products);
    }

    @GetMapping("/{field}")
    public APIResponse<List<Product>> getProductsWithSortField(@PathVariable String field){
        List<Product> products = productService.findProductsWithSorting(field);
        return new APIResponse<>(products.size(), products);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<List<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        List<Product> products = productService.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(products.size(), products);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<List<Product>> getProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        List<Product> products = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(products.size(), products);
    }


}

package com.example.pagingandsorting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class PagingAndSortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagingAndSortingApplication.class, args);
    }


    @Bean
    CommandLineRunner clr(ProductRepository productRepository){
        return args -> {
            List<Product> products = IntStream.rangeClosed(1, 200)
                    .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
                    .collect(Collectors.toList());
            productRepository.saveAll(products);
        };
    }
}

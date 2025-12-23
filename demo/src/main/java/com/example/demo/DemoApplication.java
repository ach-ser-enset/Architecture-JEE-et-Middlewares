package com.example.demo;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        productRepository.save(new Product(null, "PC", 5000.0));
        productRepository.save(new Product(null, "Imprimante", 1200.0));
        productRepository.save(new Product(null, "Smartphone", 3000.0));
        productRepository.findAll().forEach(System.out::println);
    }
}
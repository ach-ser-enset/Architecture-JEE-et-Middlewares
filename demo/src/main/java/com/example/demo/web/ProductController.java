package com.example.demo.web;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String listProducts(Model model, @RequestParam(defaultValue = "") String keyword) {
        model.addAttribute("products", productRepository.findByNameContains(keyword));
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword) {
        productRepository.deleteById(id);
        return "redirect:/products?keyword=" + keyword;
    }

    @GetMapping("/formProduct")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "formProduct";
    }

    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formProduct";
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/editProduct")
    public String editProduct(Model model, Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "formProduct";
    }
}
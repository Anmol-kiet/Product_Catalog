package com.example.productcatalog.controller;

import com.example.productcatalog.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Show the form to add a new product
    @GetMapping("/products/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";  // Thymeleaf template for the form
    }

    // Handle form submission to save a new product
    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";  // Redirect to product list after saving
    }

    // Display the list of products
    @GetMapping({"/products", "/products/view"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";  // Thymeleaf template for product list
    }

    // Show the form to edit an existing product
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> productOpt = productService.getProductById(id);
        if (productOpt.isEmpty()) {
            // Product not found, redirect to list
            return "redirect:/products";
        }
        model.addAttribute("product", productOpt.get());
        return "add-product";  // reuse add-product.html for editing
    }

    // Handle form submission to update the product
    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Delete a product by id
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}

package com.geekbrains.springdata.controllers;

import com.geekbrains.springdata.entities.Product;
import com.geekbrains.springdata.exceptions.ResourceNotFoundException;
import com.geekbrains.springdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductByID(id);
    }



    @GetMapping("/products/price_between")
    public List<Product> findProductsByPriceBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue =  "2147483647") Integer max) {
        System.out.println(min);
        System.out.println(max);
        return productService.findProductsByPriceBetween(min, max);
    }

    @PostMapping("/products/getProduct")
    public void getProduct(@RequestBody Product p){
        productService.saveProduct(p);
    }
}

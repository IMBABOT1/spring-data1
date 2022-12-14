package com.geekbrains.springdata.controllers;

import com.geekbrains.springdata.entities.Product;
import com.geekbrains.springdata.exceptions.ResourceNotFoundException;
import com.geekbrains.springdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_price", required = false) Integer minPrice,
                                        @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam(name = "title_part", required = false) String titlePart) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, titlePart, page);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @PostMapping()
    public void getProduct(@RequestBody Product p) {
        p.setId(null);
        productService.saveProduct(p);
    }


    @PutMapping()
    public void updateProduct(@RequestBody Product p) {
        productService.saveProduct(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductByID(id);
    }
}

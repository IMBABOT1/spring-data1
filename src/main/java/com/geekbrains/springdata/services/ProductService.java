package com.geekbrains.springdata.services;

import com.geekbrains.springdata.entities.Product;
import com.geekbrains.springdata.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }


    public List<Product> findProductsByPriceBetween(Integer min, Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public void deleteProductByID(Long id) {
         productRepository.deleteById(id);
    }

    public void saveProduct(Product p) {
        productRepository.save(p);
    }
}

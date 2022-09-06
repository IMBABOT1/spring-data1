package com.geekbrains.springdata.services;

import com.geekbrains.springdata.entities.Product;
import com.geekbrains.springdata.repositories.ProductRepository;
import com.geekbrains.springdata.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> find(Integer minPrice, Integer maxPrice, String partName, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partName != null) {
            spec = spec.and(ProductSpecifications.titleLike(partName));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }


    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }


    public void deleteProductByID(Long id) {
         productRepository.deleteById(id);
    }

    public void saveProduct(Product p) {
        productRepository.save(p);
    }
}

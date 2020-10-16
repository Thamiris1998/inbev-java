package com.application.service;

import com.application.repository.ProductRepository;
import com.application.domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }

    public List<Product> findByName(String name) {
        return repository.findProductByName(name);
    }

    public Product updateName(Product product, String name) {
        product.setName(name);
        return repository.save(product);
    }

    public Product create(Product product) {
       return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public List<Product> findOrderByName(Sort orderByName) {
        return repository.findAll(orderByName);
    }
}

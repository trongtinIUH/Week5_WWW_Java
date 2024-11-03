package com.example.baitap_9_21.backend.business;

import com.example.baitap_9_21.backend.repositories.ProductRepository;
import com.example.baitap_9_21.backend.repositories.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ProductBean implements ProductBeanRemote {
    @Inject
    private ProductRepository productRepository;

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }
}

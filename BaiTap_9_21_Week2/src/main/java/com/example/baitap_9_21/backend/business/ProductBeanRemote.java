package com.example.baitap_9_21.backend.business;

import com.example.baitap_9_21.backend.repositories.entities.Product;
import jakarta.ejb.Local;

import java.util.List;
@Local
public interface ProductBeanRemote {
    void add(Product product);
    //    void update(Product product);
    void delete(Product product);
    Product getProduct(long id);
    List<Product> getProducts();
    void update(Product product);
}

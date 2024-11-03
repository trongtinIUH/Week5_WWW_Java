package com.example.baitap_9_21.backend.business;

import com.example.baitap_9_21.backend.repositories.entities.ProductPrice;
import jakarta.ejb.Local;

@Local
public interface ProductPriceRemote {
    void add(ProductPrice productPrice);
    void update(ProductPrice productPrice);
    void delete(ProductPrice productPrice);
    ProductPrice getProductPrice(int id);
}

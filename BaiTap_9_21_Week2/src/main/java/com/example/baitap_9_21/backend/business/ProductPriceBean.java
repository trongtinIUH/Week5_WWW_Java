package com.example.baitap_9_21.backend.business;

import com.example.baitap_9_21.backend.repositories.ProductPriceRepository;
import com.example.baitap_9_21.backend.repositories.entities.ProductPrice;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ProductPriceBean implements  ProductPriceRemote{
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Override
    public void add(ProductPrice productPrice) {
        productPriceRepository.save(productPrice);
    }

    @Override
    public void update(ProductPrice productPrice) {
//        productPriceRespository.save(productPrice);
    }

    @Override
    public void delete(ProductPrice productPrice) {

    }

    @Override
    public ProductPrice getProductPrice(int id) {
        return null;
    }
}

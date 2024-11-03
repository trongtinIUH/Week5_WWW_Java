package com.example.baitap_9_21.backend.repositories;

import com.example.baitap_9_21.backend.repositories.entities.ProductPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductPriceRepository {

    @PersistenceContext(unitName = "baitap_9_21")
    private EntityManager em;

    public void save(ProductPrice productPrice) {
        em.persist(productPrice);
    }
    public ProductPrice findByid(int id) {
        return em.find(ProductPrice.class, id);
    }
    public List<ProductPrice> findAll() {
        return em.createNamedQuery("Product_price.findAll", ProductPrice.class).getResultList();
    }

}

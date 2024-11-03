package com.example.baitap_9_21.backend.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "product_price")
@NamedQueries({
        @NamedQuery(name = "Product_price.findAll", query = "select pc from ProductPrice pc"),
        @NamedQuery(name = "Product_price.findById", query = "select pc from ProductPrice pc where pc.id = :id")
})
public class ProductPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "value", nullable = false)
    private Double value;

    @NotNull
    @Column(name = "apply_date", nullable = false)
    private LocalDate applyDate;

    @Size(max = 250)
    @Column(name = "note", length = 250)
    private String note;

    @NotNull
    @Column(name = "state", nullable = false)
    private Byte state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

}
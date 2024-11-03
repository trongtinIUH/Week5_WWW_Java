package com.example.baitap_9_21.backend.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
        @NamedQuery(name = "Product.findById", query = "select p from Product p where p.id = :id"),
//        @NamedQuery(name = "Product.findAllJoinPrice", query = "select * from ProductPrice pc join Product p on pc.id = p.id")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;
    //    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private Set<ProductPrice> productPrices = new HashSet<>();
    @Size(max = 250)
    @NotNull
    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @Size(max = 250)
    @Column(name = "img_path", length = 250)
    private String imgPath;
    public Product(){
    }
    public Product(String name, String description, String imgPath) {
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
    }

//    public Set<ProductPrice> getProductPrices() {
//        return productPrices;
//    }
//
//    public void setProductPrices(Set<ProductPrice> productPrices) {
//        this.productPrices = productPrices;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
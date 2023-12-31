package gsc.projects.orderservice.model.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gsc.projects.orderservice.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "product_type")
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    private double price;

    private int quantity;

    @ManyToMany
    @JsonIgnore
    private List<Order> orders;

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Product product;

        public ProductBuilder() {
            this.product = new Product();
        }

        public ProductBuilder withName(String name){
            product.setName(name.toUpperCase());
            return this;
        }

        public ProductBuilder withProductType(ProductType productType){
            product.setProductType(productType);
            return this;
        }

        public ProductBuilder withPrice(double price){
            product.setPrice(price);
            return this;
        }

        public ProductBuilder withQuantity(int quantity){
            product.setQuantity(quantity);
            return this;
        }
        public Product build(){
            return product;
        }

    }
}

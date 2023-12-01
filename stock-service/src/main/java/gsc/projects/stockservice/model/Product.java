package gsc.projects.stockservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int quantity;

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private final Product product;

        public ProductBuilder() {
            this.product = new Product();
        }

        public ProductBuilder withName(String name){
            product.setName(name);
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

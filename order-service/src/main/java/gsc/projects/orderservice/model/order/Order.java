package gsc.projects.orderservice.model.order;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gsc.projects.orderservice.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JsonIgnore
    private List<Product> products;

    @Column(name = "total_price")
    private double totalPrice;

    private String userEmail;

    public static OrderBuilder builder(){
        return new OrderBuilder();
    }

    public static class OrderBuilder{
        private Order order;
        public OrderBuilder() {
            this.order = new Order();
        }

        public OrderBuilder withProducts(List<Product> products){
            order.setProducts(products);
            return this;
        }

        public OrderBuilder withPrice(double price){
            order.setTotalPrice(price);
            return this;
        }

        public OrderBuilder withUserEmail(String userEmail){
            order.setUserEmail(userEmail);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}

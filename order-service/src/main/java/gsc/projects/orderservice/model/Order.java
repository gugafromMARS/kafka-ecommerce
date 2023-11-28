package gsc.projects.orderservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

        public Order build(){
            return order;
        }
    }
}

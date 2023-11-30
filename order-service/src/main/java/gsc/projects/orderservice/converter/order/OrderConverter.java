package gsc.projects.orderservice.converter.order;


import gsc.projects.basedomains.events.OrderEvent;
import gsc.projects.orderservice.converter.product.ProductConverter;
import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.model.product.ProductOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderConverter {

    private final ProductConverter productConverter;


    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .products(order.getProducts().stream()
                        .map(product -> productConverter.toDto(product))
                        .toList())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public Order fromCreateDto(OrderCreateDto orderCreateDto){
        double orderPrice = 0;
        Order order = Order.builder()
                .withProducts(orderCreateDto.getProductOrders().stream()
                        .map(productOrder -> productConverter.fromProductOrder(productOrder))
                        .toList())
                .build();
        for(Product p : order.getProducts()){
            List<Order> orderList = p.getOrders();
            orderList.add(order);
            for(ProductOrder pO : orderCreateDto.getProductOrders()){
                if(p.getName().equals(pO.getName().toUpperCase())){
                    orderPrice += p.getPrice() * pO.getQuantity();
                    break;
                }
            }
        }
        order.setTotalPrice(orderPrice);
        return order;
    }

    public OrderEvent fromOrder(Order order){
        return OrderEvent.builder()
                .products(order.getProducts().stream()
                        .map(product -> productConverter.fromProduct(product))
                        .toList())
                .build();
    }
}

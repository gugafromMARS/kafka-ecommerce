package gsc.projects.orderservice.converter.order;


import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.basedomains.events.OrderEvent;
import gsc.projects.basedomains.events.ShippingOrder;
import gsc.projects.basedomains.events.ShippingProduct;
import gsc.projects.orderservice.converter.product.ProductConverter;
import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.service.order.APIUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderConverter {

    private final ProductConverter productConverter;

    private APIUser apiUser;

    public ShippingOrder fromOrderToShipping(Order order){
        UserDto userDto = apiUser.get(order.getUserEmail());
        List<ShippingProduct> shippingProducts = new ArrayList<>();
        for(Product p : order.getProducts()){
            shippingProducts.add(productConverter.fromProductToShopping(p));
        }
        ShippingOrder shippingOrder = ShippingOrder.builder()
                .shippingProducts(shippingProducts)
                .totalPrice(order.getTotalPrice())
                .userName(userDto.getName())
                .userAddress(userDto.getAddress())
                .build();
        return shippingOrder;
    }


    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .products(order.getProducts().stream()
                        .map(product -> productConverter.toDto(product))
                        .toList())
                .totalPrice(order.getTotalPrice())
                .userEmail(order.getUserEmail())
                .build();
    }

    public Order fromCreateDto(OrderCreateDto orderCreateDto){
        double orderPrice = 0;
        int index = 0;
        List<Integer> quantites = new ArrayList<>();
        Order order = new Order().builder()
                .withProducts(orderCreateDto.getProductOrders().stream()
                        .map(productOrder -> {
                            quantites.add(productOrder.getQuantity());
                            return productConverter.fromProductOrder(productOrder);
                        })
                        .toList())
                .withUserEmail(orderCreateDto.getUserEmail())
                .build();
        for(Product p : order.getProducts()){
            orderPrice += p.getPrice() * quantites.get(index);
            index++;
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

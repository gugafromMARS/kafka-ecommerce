package gsc.projects.orderservice.converter.order;


import gsc.projects.orderservice.converter.product.ProductConverter;
import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.model.product.ProductOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    private ProductConverter productConverter;

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
            for(ProductOrder pO : orderCreateDto.getProductOrders()){
                if(p.getName().equals(pO.getName())){
                    orderPrice += p.getPrice() * pO.getQuantity();
                    break;
                }
            }
        }
        order.setTotalPrice(orderPrice);
        return order;
    }
}

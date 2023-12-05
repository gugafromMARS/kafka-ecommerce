package gsc.projects.orderservice.service.order;


import gsc.projects.basedomains.events.ShippingOrder;
import gsc.projects.orderservice.converter.order.OrderConverter;
import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.kafka.producer.OrderProducer;
import gsc.projects.orderservice.kafka.producer.ShippingProducer;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.repository.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final OrderProducer orderProducer;
    private final ShippingProducer shippingProducer;

    @Override
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        Order newOrder = orderConverter.fromCreateDto(orderCreateDto);
        orderRepository.save(newOrder);
        orderProducer.sendOrderEvent(orderConverter.fromOrder(newOrder));
        sendShippingOrder(newOrder);
        return orderConverter.toDto(newOrder);
    }

    public void sendShippingOrder(Order order){
        ShippingOrder shippingOrder = orderConverter.fromOrderToShipping(order);
        shippingProducer.sendShippingOrder(shippingOrder);
    }
    @Override
    public OrderDto getById(Long orderId) {
        Order existingOrder =  orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return orderConverter.toDto(existingOrder);
    }

    @Override
    public void deleteById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrderByUserEmail(String userEmail) {
        return orderRepository.findByUserEmail(userEmail).stream()
                .map(order -> orderConverter.toDto(order))
                .toList();
    }

}

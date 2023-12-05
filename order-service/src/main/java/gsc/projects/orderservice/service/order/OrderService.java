package gsc.projects.orderservice.service.order;

import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderCreateDto orderCreateDto);

    OrderDto getById(Long orderId);

    void deleteById(Long orderId);

    List<OrderDto> getOrderByUserEmail(String userEmail);
}

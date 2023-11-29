package gsc.projects.orderservice.service.order;

import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderCreateDto orderCreateDto);

    OrderDto getById(Long orderId);

    void deleteById(Long orderId);
}

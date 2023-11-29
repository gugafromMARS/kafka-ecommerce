package gsc.projects.orderservice.service.order;


import gsc.projects.orderservice.converter.order.OrderConverter;
import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.repository.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        Order newOrder = orderConverter.fromCreateDto(orderCreateDto);
        orderRepository.save(newOrder);
        return orderConverter.toDto(newOrder);
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

}

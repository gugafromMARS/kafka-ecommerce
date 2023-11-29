package gsc.projects.orderservice.controller.order;


import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderCreateDto orderCreateDto){
        return new ResponseEntity<>(orderService.createOrder(orderCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> get(@PathVariable ("orderId") Long orderId){
        return ResponseEntity.ok(orderService.getById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> delete(@PathVariable ("orderId") Long orderId){
        orderService.deleteById(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}

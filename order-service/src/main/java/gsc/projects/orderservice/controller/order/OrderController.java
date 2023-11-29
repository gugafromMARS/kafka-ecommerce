package gsc.projects.orderservice.controller.order;


import gsc.projects.orderservice.dto.order.OrderCreateDto;
import gsc.projects.orderservice.service.order.OrderServiceImp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImp orderServiceImp;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderCreateDto orderCreateDto){
        return new ResponseEntity<>(orderServiceImp.createOrder(orderCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> get(@PathVariable ("orderId") Long orderId){
        return ResponseEntity.ok(orderServiceImp.getById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> delete(@PathVariable ("orderId") Long orderId){
        orderServiceImp.deleteById(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}

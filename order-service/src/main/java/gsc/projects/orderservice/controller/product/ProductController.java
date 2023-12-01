package gsc.projects.orderservice.controller.product;


import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductUpdateDto;
import gsc.projects.orderservice.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateDto productCreateDto){
        return new ResponseEntity<>(productService.createProduct(productCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<?> get(@PathVariable ("productName") String productName){
        return ResponseEntity.ok(productService.getByName(productName));
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productName}/orders")
    public ResponseEntity<?> getOrders(@PathVariable ("productName") String productName){
        return ResponseEntity.ok(productService.getAllOrders(productName));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable ("productId") Long productId){
        productService.deleteById(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable ("productId") Long productId, @RequestBody ProductUpdateDto productUpdateDto){
        return ResponseEntity.ok(productService.updateById(productId, productUpdateDto));
    }
}

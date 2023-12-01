package gsc.projects.stockservice.controller;


import gsc.projects.stockservice.service.StockServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stock/products")
@AllArgsConstructor
public class StockController {


    private final StockServiceImp stockServiceImp;
    @GetMapping("/{productId}")
    public ResponseEntity<?> get(@PathVariable ("productId") Long productId){
        return ResponseEntity.ok(stockServiceImp.getById(productId));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(stockServiceImp.getAllProducts());
    }
}
